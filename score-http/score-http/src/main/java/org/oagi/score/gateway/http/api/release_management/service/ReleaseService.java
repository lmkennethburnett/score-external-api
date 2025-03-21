package org.oagi.score.gateway.http.api.release_management.service;

import org.apache.commons.io.FileUtils;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.types.ULong;
import org.oagi.score.data.Release;
import org.oagi.score.export.ExportContext;
import org.oagi.score.export.impl.StandaloneExportContextBuilder;
import org.oagi.score.export.impl.XMLExportSchemaModuleVisitor;
import org.oagi.score.export.model.SchemaModule;
import org.oagi.score.export.service.CoreComponentService;
import org.oagi.score.gateway.http.api.module_management.data.ExportStandaloneSchemaResponse;
import org.oagi.score.gateway.http.api.release_management.data.*;
import org.oagi.score.gateway.http.api.release_management.provider.ReleaseDataProvider;
import org.oagi.score.gateway.http.configuration.security.SessionService;
import org.oagi.score.gateway.http.event.ReleaseCleanupEvent;
import org.oagi.score.gateway.http.event.ReleaseCreateRequestEvent;
import org.oagi.score.gateway.http.helper.Zip;
import org.oagi.score.redis.event.EventListenerContainer;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.ReleaseRecord;
import org.oagi.score.repo.api.user.model.ScoreUser;
import org.oagi.score.repo.component.release.ReleaseRepository;
import org.oagi.score.repo.component.release.ReleaseRepositoryDiscardRequest;
import org.oagi.score.repository.CoreComponentRepositoryForRelease;
import org.oagi.score.service.common.data.AppUser;
import org.oagi.score.service.common.data.PageRequest;
import org.oagi.score.service.common.data.PageResponse;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.oagi.score.gateway.http.api.release_management.data.ReleaseState.Published;
import static org.oagi.score.repo.api.impl.jooq.entity.Tables.*;
import static org.oagi.score.repo.api.user.model.ScoreRole.ADMINISTRATOR;

@Service
@Transactional(readOnly = true)
public class ReleaseService implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DSLContext dslContext;

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private CoreComponentRepositoryForRelease coreComponentRepositoryForRelease;

    @Autowired
    private CoreComponentService coreComponentService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ReleaseRepository repository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private EventListenerContainer eventListenerContainer;

    private final String RELEASE_CREATE_REQUEST_EVENT = "releaseCreateRequestEvent";
    private final String RELEASE_CLEANUP_EVENT = "releaseCleanupEvent";

    @Override
    public void afterPropertiesSet() throws Exception {
        eventListenerContainer.addMessageListener(this, "onReleaseCreateRequestEventReceived",
                new ChannelTopic(RELEASE_CREATE_REQUEST_EVENT));
        eventListenerContainer.addMessageListener(this, "onReleaseCleanupEventReceived",
                new ChannelTopic(RELEASE_CLEANUP_EVENT));
    }

    public List<SimpleRelease> getSimpleReleases(SimpleReleasesRequest request) {
        AppUser requester = sessionService.getAppUserByUsername(request.getUser());

        List<Condition> conditions = new ArrayList();
        conditions.add(RELEASE.LIBRARY_ID.eq(ULong.valueOf(request.getLibraryId())));
        if (!request.getStates().isEmpty()) {
            conditions.add(RELEASE.STATE.in(request.getStates()));
        }

        List<SimpleRelease> releases = dslContext.select(RELEASE.RELEASE_ID, RELEASE.RELEASE_NUM, RELEASE.STATE)
                .from(RELEASE)
                .where(conditions)
                .orderBy(RELEASE.RELEASE_ID.desc())
                .fetch(simpleReleaseMapper());

        SimpleRelease workingRelease =
                releases.stream().filter(e -> e.isWorkingRelease()).findAny().orElse(null);
        releases.remove(workingRelease);

        if (requester.isDeveloper()) {
            releases.add(0, workingRelease);
        } else {
            releases.add(workingRelease);
        }

        return releases;
    }

    private RecordMapper<Record, SimpleRelease> simpleReleaseMapper() {
        return record -> {
            SimpleRelease simpleRelease = new SimpleRelease();
            simpleRelease.setReleaseId(record.getValue(RELEASE.RELEASE_ID).toBigInteger());
            simpleRelease.setReleaseNum(record.getValue(RELEASE.RELEASE_NUM));
            simpleRelease.setState(ReleaseState.valueOf(record.getValue(RELEASE.STATE)));
            simpleRelease.setWorkingRelease("Working".equals(simpleRelease.getReleaseNum()));
            return simpleRelease;
        };
    }

    public SimpleRelease getSimpleReleaseByReleaseId(BigInteger releaseId) {
        return dslContext.select(RELEASE.RELEASE_ID, RELEASE.RELEASE_NUM, RELEASE.STATE)
                .from(RELEASE)
                .where(RELEASE.RELEASE_ID.eq(ULong.valueOf(releaseId)))
                .fetchOne(simpleReleaseMapper());
    }

    public List<ReleaseList> getReleaseList(AuthenticatedPrincipal user) {
        List<ReleaseList> releaseLists = dslContext.select(
                        RELEASE.RELEASE_ID,
                        RELEASE.GUID,
                        RELEASE.RELEASE_NUM,
                        RELEASE.RELEASE_NOTE,
                        RELEASE.RELEASE_LICENSE,
                        RELEASE.STATE,
                        APP_USER.as("creator").LOGIN_ID.as("created_by"),
                        RELEASE.CREATION_TIMESTAMP,
                        APP_USER.as("updater").LOGIN_ID.as("last_updated_by"),
                        RELEASE.LAST_UPDATE_TIMESTAMP)
                .from(RELEASE)
                .join(APP_USER.as("creator"))
                .on(RELEASE.CREATED_BY.eq(APP_USER.APP_USER_ID))
                .join(APP_USER.as("updater"))
                .on(RELEASE.LAST_UPDATED_BY.eq(APP_USER.APP_USER_ID))
                .fetchInto(ReleaseList.class);
        return releaseLists;
    }

    private SelectOnConditionStep<Record10<
            ULong, String, String, String, String,
            String, String, LocalDateTime, String, LocalDateTime>> getSelectOnConditionStep() {
        return dslContext.select(
                        RELEASE.RELEASE_ID,
                        RELEASE.GUID,
                        RELEASE.RELEASE_NUM,
                        RELEASE.RELEASE_NOTE,
                        RELEASE.RELEASE_LICENSE,
                        RELEASE.STATE,
                        APP_USER.as("creator").LOGIN_ID.as("created_by"),
                        RELEASE.CREATION_TIMESTAMP,
                        APP_USER.as("updater").LOGIN_ID.as("last_updated_by"),
                        RELEASE.LAST_UPDATE_TIMESTAMP)
                .from(RELEASE)
                .join(LIBRARY)
                .on(LIBRARY.LIBRARY_ID.eq(RELEASE.LIBRARY_ID))
                .join(APP_USER.as("creator"))
                .on(RELEASE.CREATED_BY.eq(APP_USER.as("creator").APP_USER_ID))
                .join(APP_USER.as("updater"))
                .on(RELEASE.LAST_UPDATED_BY.eq(APP_USER.as("updater").APP_USER_ID));
    }

    public PageResponse<ReleaseList> getReleases(AuthenticatedPrincipal user, ReleaseListRequest request) {
        SelectOnConditionStep<Record10<
                ULong, String, String, String, String,
                String, String, LocalDateTime, String, LocalDateTime>> step = getSelectOnConditionStep();

        List<Condition> conditions = new ArrayList();
        conditions.add(LIBRARY.LIBRARY_ID.eq(ULong.valueOf(request.getLibraryId())));
        if (StringUtils.hasLength(request.getReleaseNum())) {
            conditions.add(RELEASE.RELEASE_NUM.containsIgnoreCase(request.getReleaseNum().trim()));
        }
        if (!request.getExcludes().isEmpty()) {
            conditions.add(RELEASE.RELEASE_NUM.notIn(request.getExcludes()));
        }
        if (!request.getStates().isEmpty()) {
            conditions.add(RELEASE.STATE.in(request.getStates()));
        }
        if (request.getNamespaces() != null && !request.getNamespaces().isEmpty()) {
            conditions.add(RELEASE.NAMESPACE_ID.in(request.getNamespaces()));
        }
        if (!request.getCreatorLoginIds().isEmpty()) {
            conditions.add(APP_USER.as("creator").LOGIN_ID.in(request.getCreatorLoginIds()));
        }
        if (request.getCreateStartDate() != null) {
            conditions.add(RELEASE.CREATION_TIMESTAMP.greaterOrEqual(new Timestamp(request.getCreateStartDate().getTime()).toLocalDateTime()));
        }
        if (request.getCreateEndDate() != null) {
            conditions.add(RELEASE.CREATION_TIMESTAMP.lessThan(new Timestamp(request.getCreateEndDate().getTime()).toLocalDateTime()));
        }
        if (!request.getUpdaterLoginIds().isEmpty()) {
            conditions.add(APP_USER.as("updater").LOGIN_ID.in(request.getUpdaterLoginIds()));
        }
        if (request.getUpdateStartDate() != null) {
            conditions.add(RELEASE.LAST_UPDATE_TIMESTAMP.greaterOrEqual(new Timestamp(request.getUpdateStartDate().getTime()).toLocalDateTime()));
        }
        if (request.getUpdateEndDate() != null) {
            conditions.add(RELEASE.LAST_UPDATE_TIMESTAMP.lessThan(new Timestamp(request.getUpdateEndDate().getTime()).toLocalDateTime()));
        }

        SelectConnectByStep<Record10<
                ULong, String, String, String, String,
                String, String, LocalDateTime, String, LocalDateTime>> conditionStep = step.where(conditions);
        PageRequest pageRequest = request.getPageRequest();
        String sortDirection = pageRequest.getSortDirection();
        SortField sortField = null;
        switch (pageRequest.getSortActive()) {
            case "releaseNum":
                if ("asc".equals(sortDirection)) {
                    sortField = RELEASE.RELEASE_NUM.asc();
                } else if ("desc".equals(sortDirection)) {
                    sortField = RELEASE.RELEASE_NUM.desc();
                }

                break;

            case "state":
                if ("asc".equals(sortDirection)) {
                    sortField = RELEASE.STATE.asc();
                } else if ("desc".equals(sortDirection)) {
                    sortField = RELEASE.STATE.desc();
                }

                break;

            case "creationTimestamp":
                if ("asc".equals(sortDirection)) {
                    sortField = RELEASE.CREATION_TIMESTAMP.asc();
                } else if ("desc".equals(sortDirection)) {
                    sortField = RELEASE.CREATION_TIMESTAMP.desc();
                }

                break;

            case "lastUpdateTimestamp":
                if ("asc".equals(sortDirection)) {
                    sortField = RELEASE.LAST_UPDATE_TIMESTAMP.asc();
                } else if ("desc".equals(sortDirection)) {
                    sortField = RELEASE.LAST_UPDATE_TIMESTAMP.desc();
                }

                break;
        }
        int pageCount = dslContext.fetchCount(conditionStep);
        SelectWithTiesAfterOffsetStep<Record10<
                ULong, String, String, String, String,
                String, String, LocalDateTime, String, LocalDateTime>> offsetStep = null;
        if (sortField != null) {
            offsetStep = conditionStep.orderBy(sortField)
                    .limit(pageRequest.getOffset(), pageRequest.getPageSize());
        } else {
            if (pageRequest.getPageIndex() >= 0 && pageRequest.getPageSize() > 0) {
                offsetStep = conditionStep
                        .limit(pageRequest.getOffset(), pageRequest.getPageSize());
            }
        }

        List<ReleaseList> result = (offsetStep != null) ?
                offsetStep.fetchInto(ReleaseList.class) : conditionStep.fetchInto(ReleaseList.class);

        PageResponse<ReleaseList> response = new PageResponse();
        response.setList(result);
        response.setPage(pageRequest.getPageIndex());
        response.setSize(pageRequest.getPageSize());
        response.setLength(pageCount);
        return response;
    }

    @Transactional
    public ReleaseResponse createRelease(ScoreUser user, ReleaseDetail releaseDetail) {
        BigInteger userId = user.getUserId();
        ReleaseResponse response = new ReleaseResponse();

        ReleaseRecord releaseRecord = repository.create(userId,
                releaseDetail.getReleaseNum(),
                releaseDetail.getReleaseNote(),
                releaseDetail.getReleaseLicense(),
                releaseDetail.getLibraryId(),
                releaseDetail.getNamespaceId());

        response.setStatus("success");
        response.setStatusMessage("");

        releaseDetail.setReleaseId(releaseRecord.getReleaseId().toBigInteger());
        if (releaseRecord.getNamespaceId() != null) {
            releaseDetail.setNamespaceId(releaseRecord.getNamespaceId().toBigInteger());
        }
        releaseDetail.setReleaseNum(releaseRecord.getReleaseNum());
        releaseDetail.setReleaseNote(releaseRecord.getReleaseNote());
        releaseDetail.setReleaseLicense(releaseRecord.getReleaseLicense());
        releaseDetail.setState(releaseRecord.getState());
        response.setReleaseDetail(releaseDetail);

        return response;
    }

    @Transactional
    public void updateRelease(AuthenticatedPrincipal user, ReleaseDetail releaseDetail) {
        BigInteger userId = sessionService.userId(user);

        repository.update(userId,
                releaseDetail.getReleaseId(),
                releaseDetail.getReleaseNum(),
                releaseDetail.getReleaseNote(),
                releaseDetail.getReleaseLicense(),
                releaseDetail.getNamespaceId());
    }

    public ReleaseDetail getReleaseDetail(AuthenticatedPrincipal user, BigInteger releaseId) {
        Release release = repository.findById(releaseId);
        ReleaseDetail detail = new ReleaseDetail();
        detail.setReleaseId(release.getReleaseId());
        detail.setLibraryId(release.getLibraryId());
        detail.setNamespaceId(release.getNamespaceId());
        detail.setReleaseNum(release.getReleaseNum());
        detail.setReleaseNote(release.getReleaseNote());
        detail.setReleaseLicense(release.getReleaseLicense());
        detail.setState(release.getState());
        detail.setLatestRelease(repository.isLatestRelease(releaseId));
        return detail;
    }

    @Transactional
    public void discard(AuthenticatedPrincipal user, List<BigInteger> releaseIds) {
        for (BigInteger releaseId : releaseIds) {
            ReleaseRepositoryDiscardRequest request = new ReleaseRepositoryDiscardRequest(user, releaseId);
            repository.discard(request);
        }
    }

    public AssignComponents getAssignComponents(BigInteger releaseId) {
        return repository.getAssignComponents(releaseId);
    }

    @Transactional
    public void transitState(AuthenticatedPrincipal user,
                             TransitStateRequest request) {

        ScoreUser scoreUser = sessionService.asScoreUser(user);
        ReleaseState requestState = ReleaseState.valueOf(request.getState());
        if (requestState == Published && !scoreUser.hasRole(ADMINISTRATOR)) {
            throw new IllegalArgumentException("Only administrators can publish the release.");
        }

        repository.transitState(user, request);
        if (Published == requestState) {
            // fire the create release draft event.
            ReleaseCleanupEvent releaseCleanupEvent = new ReleaseCleanupEvent(
                    scoreUser.getUserId(), request.getReleaseId());

            /*
             * Message Publishing
             */
            redisTemplate.convertAndSend(RELEASE_CLEANUP_EVENT, releaseCleanupEvent);
        }
    }

    public ReleaseValidationResponse validate(AuthenticatedPrincipal user,
                                              ReleaseValidationRequest request) {

        ReleaseValidator validator = new ReleaseValidator(request.getReleaseId(), dslContext);
        validator.setAssignedAccComponentManifestIds(request.getAssignedAccComponentManifestIds());
        validator.setAssignedAsccpComponentManifestIds(request.getAssignedAsccpComponentManifestIds());
        validator.setAssignedBccpComponentManifestIds(request.getAssignedBccpComponentManifestIds());
        validator.setAssignedCodeListComponentManifestIds(request.getAssignedCodeListComponentManifestIds());
        validator.setAssignedDtComponentManifestIds(request.getAssignedDtComponentManifestIds());
        return validator.validate();
    }

    @Transactional
    public ReleaseValidationResponse createDraft(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                                 @RequestBody ReleaseValidationRequest request) {
        BigInteger releaseId = request.getReleaseId();
        if (repository.isThereAnyDraftRelease(releaseId)) {
            throw new IllegalArgumentException("It cannot make any release to 'Draft' due to a release restriction.");
        }

        ReleaseValidationResponse response = this.validate(user, request);
        if (response.isSucceed()) {
            // update state to 'Release Draft' for assigned CCs
            TransitStateRequest transitStateRequest = new TransitStateRequest();
            transitStateRequest.setReleaseId(releaseId);
            transitStateRequest.setState(ReleaseState.Draft.name());
            transitStateRequest.setValidationRequest(request);

            this.transitState(user, transitStateRequest);

            // fire the create release draft event.
            ReleaseCreateRequestEvent releaseCreateRequestEvent = new ReleaseCreateRequestEvent(
                    sessionService.userId(user),
                    releaseId,
                    request.getAssignedAccComponentManifestIds(),
                    request.getAssignedAsccpComponentManifestIds(),
                    request.getAssignedBccpComponentManifestIds(),
                    request.getAssignedDtComponentManifestIds(),
                    request.getAssignedCodeListComponentManifestIds(),
                    request.getAssignedAgencyIdListComponentManifestIds());

            /*
             * Message Publishing
             */
            redisTemplate.convertAndSend(RELEASE_CREATE_REQUEST_EVENT, releaseCreateRequestEvent);

            response.clearWarnings();
        }

        return response;
    }

    /**
     * This method is invoked by 'releaseCreateRequestEvent' channel subscriber.
     *
     * @param releaseCreateRequestEvent
     */
    @Transactional
    public void onReleaseCreateRequestEventReceived(ReleaseCreateRequestEvent releaseCreateRequestEvent) {
        RLock lock = redissonClient.getLock("ReleaseCreateRequestEvent:" + releaseCreateRequestEvent.hashCode());
        if (!lock.tryLock()) {
            return;
        }
        try {
            logger.debug("Received ReleaseCreateRequestEvent: " + releaseCreateRequestEvent);
            repository.copyWorkingManifestsTo(releaseCreateRequestEvent.getReleaseId(),
                    releaseCreateRequestEvent.getAccManifestIds(),
                    releaseCreateRequestEvent.getAsccpManifestIds(),
                    releaseCreateRequestEvent.getBccpManifestIds(),
                    releaseCreateRequestEvent.getDtManifestIds(),
                    releaseCreateRequestEvent.getCodeListManifestIds(),
                    releaseCreateRequestEvent.getAgencyIdListManifestIds()
            );
            repository.updateState(releaseCreateRequestEvent.getUserId(),
                    releaseCreateRequestEvent.getReleaseId(), ReleaseState.Draft);
        } finally {
            lock.unlock();
        }
    }

    /**
     * This method is invoked by 'releaseCleanupEvent' channel subscriber.
     *
     * @param releaseCleanupEvent
     */
    @Transactional
    public void onReleaseCleanupEventReceived(ReleaseCleanupEvent releaseCleanupEvent) {
        RLock lock = redissonClient.getLock("ReleaseCleanupEvent:" + releaseCleanupEvent.hashCode());
        if (!lock.tryLock()) {
            return;
        }
        try {
            logger.debug("Received ReleaseCleanupEvent: " + releaseCleanupEvent);
            ScoreUser requester = sessionService.getScoreUserByUserId(releaseCleanupEvent.getUserId());
            repository.cleanUp(requester, releaseCleanupEvent.getReleaseId());
            repository.updateState(releaseCleanupEvent.getUserId(),
                    releaseCleanupEvent.getReleaseId(), ReleaseState.Published);
        } finally {
            lock.unlock();
        }
    }

    public GenerateMigrationScriptResponse generateMigrationScript(ScoreUser user, BigInteger releaseId) throws IOException {
        Release release = repository.findById(releaseId);

        MigrationScriptGenerator generator = new MigrationScriptGenerator(dslContext, resourceLoader,
                BigInteger.valueOf(100000000L));
        File file = generator.generate(user, release);

        String fileName = release.getReleaseNum().replace(".", "_") + ".zip";
        return new GenerateMigrationScriptResponse(fileName, file);
    }

    public ExportStandaloneSchemaResponse exportStandaloneSchema(
            ScoreUser user, List<BigInteger> asccpManifestIdList) throws Exception {
        if (asccpManifestIdList == null || asccpManifestIdList.isEmpty()) {
            throw new IllegalArgumentException();
        }

        File baseDir = new File(FileUtils.getTempDirectory(), UUID.randomUUID().toString());
        FileUtils.forceMkdir(baseDir);

        try {
            List<File> files = new ArrayList<>();

            Map<BigInteger, BigInteger> releaseIdMap = releaseRepository.getReleaseIdMapByAsccpManifestIdList(asccpManifestIdList);
            Map<BigInteger, ReleaseDataProvider> dataProviderMap = releaseIdMap.values().stream().distinct()
                    .collect(Collectors.toMap(Function.identity(), e -> new ReleaseDataProvider(coreComponentRepositoryForRelease, e)));
            Map<String, Integer> pathCounter = new ConcurrentHashMap<>();
            List<Exception> exceptions = new ArrayList<>();
            asccpManifestIdList.parallelStream().forEach(asccpManifestId -> {
                try {
                    ReleaseDataProvider dataProvider = dataProviderMap.get(releaseIdMap.get(asccpManifestId));

                    XMLExportSchemaModuleVisitor visitor = new XMLExportSchemaModuleVisitor(coreComponentService, dataProvider);
                    visitor.setBaseDirectory(baseDir);

                    StandaloneExportContextBuilder builder =
                            new StandaloneExportContextBuilder(dataProvider, coreComponentService, pathCounter);
                    ExportContext exportContext = builder.build(asccpManifestId);

                    for (SchemaModule schemaModule : exportContext.getSchemaModules()) {
                        schemaModule.visit(visitor);
                        File file = schemaModule.getModuleFile();
                        if (file != null) {
                            files.add(file);
                        }
                    }
                } catch (Exception e) {
                    logger.warn("Unexpected error occurs while it generates a stand-alone schema for 'asccp_manifest_id' [" + asccpManifestId + "]", e);
                    exceptions.add(e);
                }
            });

            if (!exceptions.isEmpty()) {
                throw new IllegalStateException(exceptions.stream().map(e -> e.getMessage()).collect(Collectors.joining("\n")));
            }

            if (files.size() == 1) {
                File srcFile = files.get(0);
                File destFile = File.createTempFile("oagis-", null);
                if (!srcFile.renameTo(destFile)) {
                    FileUtils.copyFile(srcFile, destFile);
                }
                String filename = srcFile.getName();
                return new ExportStandaloneSchemaResponse(filename, destFile);
            } else {
                return new ExportStandaloneSchemaResponse(UUID.randomUUID() + ".zip",
                        Zip.compressionHierarchy(baseDir, files));
            }
        } finally {
            FileUtils.deleteDirectory(baseDir);
        }
    }
}
