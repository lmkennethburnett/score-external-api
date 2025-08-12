package org.oagi.score.gateway.http.api.external.controller;

import static java.util.stream.Collectors.toSet;
import static org.oagi.score.gateway.http.common.util.ControllerUtils.pageRequest;
import static org.oagi.score.gateway.http.common.util.Utility.separate;
import static org.springframework.util.StringUtils.hasLength;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jooq.exception.IOException;
import org.oagi.score.gateway.http.api.application_management.service.ApplicationConfigurationService;
import org.oagi.score.gateway.http.api.bie_management.model.BieListEntryRecord;
import org.oagi.score.gateway.http.api.bie_management.model.BiePackageId;
import org.oagi.score.gateway.http.api.bie_management.model.BiePackageListEntryRecord;
import org.oagi.score.gateway.http.api.bie_management.model.BieState;
import org.oagi.score.gateway.http.api.bie_management.model.TopLevelAsbiepId;
import org.oagi.score.gateway.http.api.bie_management.model.expression.BieGenerateExpressionResult;
import org.oagi.score.gateway.http.api.bie_management.model.expression.GenerateExpressionOption;
import org.oagi.score.gateway.http.api.bie_management.repository.criteria.BieListFilterCriteria;
import org.oagi.score.gateway.http.api.bie_management.repository.criteria.BieListInBiePackageFilterCriteria;
import org.oagi.score.gateway.http.api.bie_management.repository.criteria.BiePackageListFilterCriteria;
import org.oagi.score.gateway.http.api.bie_management.service.BieGenerateService;
import org.oagi.score.gateway.http.api.bie_management.service.BiePackageQueryService;
import org.oagi.score.gateway.http.api.bie_management.service.BieQueryService;
import org.oagi.score.gateway.http.api.cc_management.model.CcType;
import org.oagi.score.gateway.http.api.cc_management.model.acc.AccManifestId;
import org.oagi.score.gateway.http.api.cc_management.model.asccp.AsccpManifestId;
import org.oagi.score.gateway.http.api.cc_management.model.bccp.BccpManifestId;
import org.oagi.score.gateway.http.api.cc_management.model.dt.DtManifestId;
import org.oagi.score.gateway.http.api.code_list_management.model.CodeListManifestId;
import org.oagi.score.gateway.http.api.context_management.business_context.model.BusinessContextId;
import org.oagi.score.gateway.http.api.external.service.ExternalBieService;
import org.oagi.score.gateway.http.api.external.service.ExternalComponentsService;
import org.oagi.score.gateway.http.api.graph.model.FindUsagesRequest;
import org.oagi.score.gateway.http.api.graph.model.FindUsagesResponse;
import org.oagi.score.gateway.http.api.graph.model.Graph;
import org.oagi.score.gateway.http.api.graph.service.GraphService;
import org.oagi.score.gateway.http.api.release_management.model.ReleaseId;
import org.oagi.score.gateway.http.api.tenant_management.service.TenantQueryService;
import org.oagi.score.gateway.http.common.model.AccessPrivilege;
import org.oagi.score.gateway.http.common.model.DateRangeCriteria;
import org.oagi.score.gateway.http.common.model.Guid;
import org.oagi.score.gateway.http.common.model.PageRequest;
import org.oagi.score.gateway.http.common.model.PageResponse;
import org.oagi.score.gateway.http.common.model.ScoreUser;
import org.oagi.score.gateway.http.configuration.security.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class ExternalBieController {

        @Autowired
        private BieQueryService bieQueryService;

        @Autowired
        private BiePackageQueryService biePackageQueryService;

        @Autowired
        private BieGenerateService bieGenerateService;

        @Autowired
        private ApplicationConfigurationService configService;

        @Autowired
        private TenantQueryService tenantService;

        @Autowired
        private SessionService sessionService;

        @Autowired
        private ExternalComponentsService externalComponentsService;

        @Autowired
        private ExternalBieService externalBieService;

        @Autowired
        private GraphService graphService;

        @RequestMapping(value = "/ext/bie_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public PageResponse<BieListEntryRecord> getBieList(
                        @AuthenticationPrincipal AuthenticatedPrincipal user,
                        @RequestParam(name = "libraryName") String libraryName,
                        @RequestParam(name = "releaseVersions", required = false) String releaseVersions,
                        @RequestParam(name = "den", required = false) String den,
                        @RequestParam(name = "propertyTerm", required = false) String propertyTerm,
                        @RequestParam(name = "businessContext", required = false) String businessContext,
                        @RequestParam(name = "version", required = false) String version,
                        @RequestParam(name = "remark", required = false) String remark,
                        @RequestParam(name = "asccpManifestId", required = false) AsccpManifestId asccpManifestId,
                        @RequestParam(name = "access", required = false) String access,
                        @RequestParam(name = "states", required = false) String states,
                        @RequestParam(name = "topLevelAsbiepIds", required = false) String topLevelAsbiepIds,
                        @RequestParam(name = "basedTopLevelAsbiepIds", required = false) String basedTopLevelAsbiepIds,
                        @RequestParam(name = "excludeTopLevelAsbiepIds", required = false) String excludeTopLevelAsbiepIds,
                        @RequestParam(name = "excludePropertyTerms", required = false) String excludePropertyTerms,
                        @RequestParam(name = "deprecated", required = false) String deprecated,
                        @RequestParam(name = "ownedByDeveloper", required = false) String ownedByDeveloper,

                        @RequestParam(name = "ownerLoginIdList", required = false) @Parameter(description = "Comma-separated list of login IDs of owners to filter the results.") String ownerLoginIdList,

                        @RequestParam(name = "updaterLoginIdList", required = false) @Parameter(description = "Comma-separated list of login IDs of updaters to filter the results.") String updaterLoginIdList,

                        @RequestParam(name = "lastUpdatedOn", required = false) @Parameter(description = "Filter results by last update timestamp range in epoch milliseconds. "
                                        +
                                        "Format: `[after~before]`. Use `after` to specify the lower bound and `before` for the upper bound.") String lastUpdatedOn,

                        @RequestParam(name = "orderBy", required = false) @Parameter(description = "Sorting criteria for the results. "
                                        +
                                        "Supports multiple comma-separated properties with an optional '+' (ascending) or '-' (descending) prefix. "
                                        +
                                        "If no prefix is specified, ascending order is applied by default. " +
                                        "Example: `-releaseNum,+lastUpdateTimestamp,state` is equivalent to `releaseNum desc, lastUpdateTimestamp asc, state asc`.") String orderBy,

                        @RequestParam(name = "pageIndex", required = false) @Parameter(description = "Index of the page to retrieve (zero-based). "
                                        +
                                        "If a negative value is provided, pagination is ignored and all results are returned.") Integer pageIndex,

                        @RequestParam(name = "pageSize", required = false) @Parameter(description = "Number of records per page. "
                                        +
                                        "If a negative value is provided, pagination is ignored and all results are returned.") Integer pageSize) {

                ScoreUser requester = sessionService.getScoreSystemUser();

                boolean tenantEnabled = configService.isTenantEnabled(requester);

                Collection<ReleaseId> releaseIdList = separate(releaseVersions)
                                .map(e -> externalComponentsService.getReleaseId(libraryName, e)).collect(toSet());

                if (!org.springframework.util.StringUtils.hasLength(states)) {
                        states=String.join(",",BieState.QA.toString(),BieState.Production.toString());
                }

                BieListFilterCriteria filterCriteria = new BieListFilterCriteria(
                                externalComponentsService.getLibraryId(libraryName),
                                releaseIdList,
                                den, propertyTerm,
                                separate(businessContext).collect(toSet()),
                                version, remark, asccpManifestId,
                                (hasLength(access)) ? AccessPrivilege.valueOf(access) : null,
                                separate(states).map(e -> BieState.valueOf(e))
                                        .filter(state -> state.getLevel() >=  BieState.QA.getLevel())
                                        .collect(toSet()),
                                separate(excludePropertyTerms).collect(toSet()),

                                separate(topLevelAsbiepIds).map(e -> TopLevelAsbiepId.from(e)).collect(toSet()),
                                separate(basedTopLevelAsbiepIds).map(e -> TopLevelAsbiepId.from(e)).collect(toSet()),
                                separate(excludeTopLevelAsbiepIds).map(e -> TopLevelAsbiepId.from(e)).collect(toSet()),

                                hasLength(deprecated) ? ("true".equalsIgnoreCase(deprecated) ? true : false) : null,
                                hasLength(ownedByDeveloper) ? ("true".equalsIgnoreCase(ownedByDeveloper) ? true : false)
                                                : null,
                                tenantEnabled,
                                (tenantEnabled && !requester.isAdministrator())
                                                ? tenantService.getUserTenantsRoleByUser(requester)
                                                : Collections.emptyList(),

                                separate(ownerLoginIdList).collect(toSet()),
                                separate(updaterLoginIdList).collect(toSet()),
                                hasLength(lastUpdatedOn) ? DateRangeCriteria.create(lastUpdatedOn) : null);

                PageRequest pageRequest = pageRequest(pageIndex, pageSize, orderBy);
                PageResponse<BieListEntryRecord> response = new PageResponse<>();

                if (!tenantEnabled) {
                        var resultAndCount = bieQueryService.getBieList(requester, filterCriteria, pageRequest);

                        response.setList(resultAndCount.result());
                        response.setLength(resultAndCount.count());
                }
                response.setPage(pageRequest.pageIndex());
                response.setSize(pageRequest.pageSize());

                return response;
        }

        @RequestMapping(value = "/ext/bie-packages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public PageResponse<BiePackageListEntryRecord> getBiePackageList(
                        @AuthenticationPrincipal AuthenticatedPrincipal user,
                        @RequestParam(name = "libraryName") String libraryName,
                        @RequestParam(name = "versionId", required = false) String versionId,
                        @RequestParam(name = "versionName", required = false) String versionName,
                        @RequestParam(name = "description", required = false) String description,
                        @RequestParam(name = "den", required = false) String den,
                        @RequestParam(name = "businessTerm", required = false) String businessTerm,
                        @RequestParam(name = "version", required = false) String version,
                        @RequestParam(name = "remark", required = false) String remark,
                        @RequestParam(name = "states", required = false) String states,
                        @RequestParam(name = "biePackageIds", required = false) String biePackageIds,
                        @RequestParam(name = "releaseIds", required = false) String releaseIds,

                        @RequestParam(name = "ownerLoginIdList", required = false) @Parameter(description = "Comma-separated list of login IDs of owners to filter the results.") String ownerLoginIdList,

                        @RequestParam(name = "updaterLoginIdList", required = false) @Parameter(description = "Comma-separated list of login IDs of updaters to filter the results.") String updaterLoginIdList,

                        @RequestParam(name = "lastUpdatedOn", required = false) @Parameter(description = "Filter results by last update timestamp range in epoch milliseconds. "
                                        +
                                        "Format: `[after~before]`. Use `after` to specify the lower bound and `before` for the upper bound.") String lastUpdatedOn,

                        @RequestParam(name = "orderBy", required = false) @Parameter(description = "Sorting criteria for the results. "
                                        +
                                        "Supports multiple comma-separated properties with an optional '+' (ascending) or '-' (descending) prefix.") String orderBy,

                        @RequestParam(name = "pageIndex", required = false, defaultValue = "0") @Parameter(description = "Index of the page to retrieve (zero-based). If negative, all results are returned.") Integer pageIndex,

                        @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Parameter(description = "Number of records per page. If negative, all results are returned.") Integer pageSize) {

                ScoreUser requester = sessionService.getScoreSystemUser();

                BiePackageListFilterCriteria filterCriteria = new BiePackageListFilterCriteria(
                                externalComponentsService.getLibraryId(libraryName),
                                versionId, versionName, description, den, businessTerm, version, remark,
                                separate(states).map(e -> BieState.valueOf(e)).collect(toSet()),
                                separate(releaseIds).map(e -> ReleaseId.from(e)).collect(toSet()),
                                separate(biePackageIds).map(e -> BiePackageId.from(e)).collect(toSet()),
                                separate(ownerLoginIdList).collect(toSet()),
                                separate(updaterLoginIdList).collect(toSet()),
                                StringUtils.hasLength(lastUpdatedOn) ? DateRangeCriteria.create(lastUpdatedOn) : null);

                PageRequest pageRequest = pageRequest(pageIndex, pageSize, orderBy);

                var resultAndCount = biePackageQueryService.getBiePackageList(
                                requester, filterCriteria, pageRequest);

                PageResponse<BiePackageListEntryRecord> response = new PageResponse<>();
                response.setList(resultAndCount.result());
                response.setPage(pageRequest.pageIndex());
                response.setSize(pageRequest.pageSize());
                response.setLength(resultAndCount.count());
                return response;

        }

        @RequestMapping(value = "/ext/bie/generate", method = RequestMethod.GET)
        public ResponseEntity<InputStreamResource> generate(
                        @RequestParam(name = "bizCtxIds", required = false) String bizCtxIds,
                        @RequestParam(name = "guid", required = true) Guid guid)
                        throws IOException, FileNotFoundException {

                TopLevelAsbiepId topLevelAsbiepId = externalBieService.getTopLevelAsbiepId(guid);

                Map<TopLevelAsbiepId, BusinessContextId> bizCtxMap = new HashMap<>();
                if (null != bizCtxIds) {
                        for (String bizCtxIdStr : bizCtxIds.split(",")) {
                                BusinessContextId bizCtxId = BusinessContextId.from(bizCtxIdStr);
                                bizCtxMap.put(topLevelAsbiepId, bizCtxId);
                        }
                }

                GenerateExpressionOption option = new GenerateExpressionOption();
                option.setBizCtxIds(bizCtxMap);
                option.setPackageOption("EACH");
                option.setExpressionOption("XML");

                BieGenerateExpressionResult bieGenerateExpressionResult = bieGenerateService.generate(
                                sessionService.getScoreSystemUser(), List.of(topLevelAsbiepId), option);

                return ResponseEntity.ok()
                                .header(HttpHeaders.CONTENT_DISPOSITION,
                                                "attachment; filename=\"" + bieGenerateExpressionResult.filename()
                                                                + "\"")
                                .contentType(MediaType.parseMediaType(bieGenerateExpressionResult.contentType()))
                                .contentLength(bieGenerateExpressionResult.file().length())
                                .body(new InputStreamResource(new FileInputStream(bieGenerateExpressionResult.file())));
        }

        @GetMapping(value = "/ext/bie-packages/{biePackageName}/{biePackageVersionId}/bies")
        public PageResponse<BieListEntryRecord> getBieListInBiePackage(
                        @AuthenticationPrincipal AuthenticatedPrincipal user,
                        @PathVariable("biePackageName") String biePackageName,
                        @PathVariable("biePackageVersionId") String biePackageVersionId,
                        @RequestParam(name = "libraryName") String libraryName,
                        @RequestParam(name = "den", required = false) String den,
                        @RequestParam(name = "businessContext", required = false) String businessContext,
                        @RequestParam(name = "version", required = false) String version,
                        @RequestParam(name = "remark", required = false) String remark,

                        @RequestParam(name = "ownerLoginIdList", required = false) @Parameter(description = "Comma-separated list of login IDs of owners to filter the results.") String ownerLoginIdList,

                        @RequestParam(name = "updaterLoginIdList", required = false) @Parameter(description = "Comma-separated list of login IDs of updaters to filter the results.") String updaterLoginIdList,

                        @RequestParam(name = "lastUpdatedOn", required = false) @Parameter(description = "Filter results by last update timestamp range in epoch milliseconds. "
                                        +
                                        "Format: `[after~before]`. Use `after` to specify the lower bound and `before` for the upper bound.") String lastUpdatedOn,

                        @RequestParam(name = "orderBy", required = false) @Parameter(description = "Sorting criteria for the results. "
                                        +
                                        "Supports multiple comma-separated properties with an optional '+' (ascending) or '-' (descending) prefix.") String orderBy,

                        @RequestParam(name = "pageIndex", required = false, defaultValue = "0") @Parameter(description = "Index of the page to retrieve (zero-based). If negative, all results are returned.") Integer pageIndex,

                        @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Parameter(description = "Number of records per page. If negative, all results are returned.") Integer pageSize) {

                PageRequest pageRequest = pageRequest(pageIndex, pageSize, orderBy);
                PageResponse<BieListEntryRecord> response = new PageResponse<>();

                BiePackageId biePackageId = externalBieService.getBiePackageId(libraryName, biePackageName,
                                biePackageVersionId);
                if (biePackageId != null) {
                        BieListInBiePackageFilterCriteria filterCriteria = new BieListInBiePackageFilterCriteria(
                                        externalBieService.getBiePackageId(libraryName, biePackageName,
                                                        biePackageVersionId),
                                        den, businessContext, version, remark,
                                        separate(ownerLoginIdList).collect(toSet()),
                                        separate(updaterLoginIdList).collect(toSet()),
                                        StringUtils.hasLength(lastUpdatedOn) ? DateRangeCriteria.create(lastUpdatedOn)
                                                        : null);

                        var resultAndCount = bieQueryService.getBieList(
                                        sessionService.getScoreSystemUser(), filterCriteria, pageRequest);

                        response.setList(resultAndCount.result());

                        response.setLength(resultAndCount.count());
                } else {
                        response.setList(List.of());
                        response.setLength(0);
                }
                response.setPage(pageRequest.pageIndex());
                response.setSize(pageRequest.pageSize());

                return response;
        }

        @RequestMapping(value = "/ext/find_usages/{type}/{id:[\\d]+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public FindUsagesResponse findUsages(@AuthenticationPrincipal AuthenticatedPrincipal user,
                        @PathVariable("type") String type,
                        @PathVariable("id") BigInteger manifestId) {
                CcType ccType = CcType.valueOf(type.toUpperCase());
                ScoreUser scoreUser = sessionService.getScoreSystemUser();

                switch (ccType) {
                        case ACC:
                                return graphService.findUsages(
                                                scoreUser,
                                                new FindUsagesRequest(ccType, new AccManifestId(manifestId)));
                        case ASCCP:
                                return graphService.findUsages(
                                                scoreUser,
                                                new FindUsagesRequest(ccType, new AsccpManifestId(manifestId)));
                        case BCCP:
                                return graphService.findUsages(
                                                scoreUser,
                                                new FindUsagesRequest(ccType, new BccpManifestId(manifestId)));
                        case DT:
                                return graphService.findUsages(
                                                scoreUser,
                                                new FindUsagesRequest(ccType, new DtManifestId(manifestId)));
                }

                throw new IllegalArgumentException("Unknown graph type " + type);
        }

    @RequestMapping(value = "/ext/graphs/{type}/{id:[\\d]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getGraph(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                        @PathVariable("type") String type,
                                        @PathVariable("id") BigInteger id,
                                        @RequestParam(value = "q", required = false) String query) {

        
        
        ScoreUser scoreUser = sessionService.getScoreSystemUser();                                        
        Graph graph;
        switch (type.toLowerCase()) {
            case "acc":
            case "extension":
                graph = graphService.getAccGraph(
                        scoreUser, new AccManifestId(id));
                break;

            case "asccp":
                graph = graphService.getAsccpGraph(
                        scoreUser, new AsccpManifestId(id), false);
                break;

            case "bccp":
                graph = graphService.getBccpGraph(
                        scoreUser, new BccpManifestId(id));
                break;

            case "dt":
                graph = graphService.getDtGraph(
                        scoreUser, new DtManifestId(id));
                break;

            case "top_level_asbiep":
                graph = graphService.getBieGraph(
                        scoreUser, new TopLevelAsbiepId(id));
                break;

            case "code_list":
                graph = graphService.getCodeListGraph(
                        scoreUser, new CodeListManifestId(id));
                break;

            default:
                throw new UnsupportedOperationException();
        }

        Map<String, Object> response = new HashMap();

        if (StringUtils.hasLength(query)) {
            Collection<List<String>> paths = graph.findPaths(type + id, query);
            response.put("query", query);
            response.put("paths", paths.stream()
                    .map(e -> e.stream()
                            .filter(item -> !item.matches("ascc\\d+|bcc\\d+|bdt\\d+"))
                            .collect(Collectors.joining(">"))
                    )
                    .collect(Collectors.toList()));
        } else {
            response.put("graph", graph);
        }

        return response;
    }


}
