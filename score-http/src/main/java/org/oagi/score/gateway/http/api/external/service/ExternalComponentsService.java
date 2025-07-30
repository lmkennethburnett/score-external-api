package org.oagi.score.gateway.http.api.external.service;

import org.apache.commons.io.FileUtils;
import org.jooq.DSLContext;
import org.jooq.Record1;

import static org.jooq.impl.DSL.*;
import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.cc_management.model.CcDocument;
import org.oagi.score.gateway.http.api.cc_management.model.CcDocumentImpl;
import org.oagi.score.gateway.http.api.cc_management.model.asccp.AsccpManifestId;
import org.oagi.score.gateway.http.api.export.ExportContext;
import org.oagi.score.gateway.http.api.export.impl.StandaloneExportContextBuilder;
import org.oagi.score.gateway.http.api.export.impl.XMLExportSchemaModuleVisitor;
import org.oagi.score.gateway.http.api.export.model.SchemaModule;
import org.oagi.score.gateway.http.api.external.model.AssociatedComponentType;
import org.oagi.score.gateway.http.api.external.model.ExternalChildComponentRecord;
import org.oagi.score.gateway.http.api.library_management.model.LibraryId;
import org.oagi.score.gateway.http.api.release_management.model.ReleaseId;
import org.oagi.score.gateway.http.api.release_management.model.ReleaseState;
import org.oagi.score.gateway.http.common.model.ExportStandaloneSchemaResponse;
import org.oagi.score.gateway.http.common.model.ScoreUser;
import org.oagi.score.gateway.http.common.repository.jooq.RepositoryFactory;
import org.oagi.score.gateway.http.common.util.Zip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.oagi.score.gateway.http.common.repository.jooq.entity.Tables.*;

@Service
@Transactional(readOnly = true)
public class ExternalComponentsService {

    @Autowired
    private RepositoryFactory repositoryFactory;

    @Autowired
    private DSLContext dslContext;


    public LibraryId getLibraryId(String libraryName) {
        Record1<ULong> libraryIdRecord = dslContext.select(
                LIBRARY.LIBRARY_ID)
                .from(LIBRARY)
                .where(LIBRARY.NAME.eq(libraryName))
                .fetchOne();
        if (libraryIdRecord == null)
            return null;
        else {
            return new LibraryId(libraryIdRecord.getValue(LIBRARY.LIBRARY_ID).toBigInteger());
        }
    }

    public ReleaseId getReleaseId(String libraryName, String releaseVersion) {

        ReleaseId releaseId = dslContext.select(
                RELEASE.RELEASE_ID)
                .from(RELEASE)
                .join(LIBRARY).on(LIBRARY.LIBRARY_ID.eq(RELEASE.LIBRARY_ID))
                .where(RELEASE.RELEASE_NUM.eq(releaseVersion).and(RELEASE.STATE.eq(ReleaseState.Published.name()))).and(LIBRARY.NAME.eq(libraryName))
                .fetchOneInto(ReleaseId.class);
        return releaseId;
    }


    public AsccpManifestId getAsccpManifestId(ReleaseId releaseId, String guid) {

        AsccpManifestId asccpManifestId = dslContext.select(
                ASCCP_MANIFEST.ASCCP_MANIFEST_ID)
                .from(ASCCP_MANIFEST)
                .join(ASCCP).on(ASCCP.ASCCP_ID.eq(ASCCP_MANIFEST.ASCCP_ID))
                .where(ASCCP.GUID.eq(guid)).and(ASCCP_MANIFEST.RELEASE_ID.eq(ULong.valueOf(releaseId.toString())))
                .fetchOneInto(AsccpManifestId.class);
        return asccpManifestId;
    }

    public ExportStandaloneSchemaResponse exportStandaloneSchema(
            ScoreUser requester, List<AsccpManifestId> asccpManifestIdList) throws Exception {
        if (asccpManifestIdList == null || asccpManifestIdList.isEmpty()) {
            throw new IllegalArgumentException();
        }

        File baseDir = new File(FileUtils.getTempDirectory(), UUID.randomUUID().toString());
        FileUtils.forceMkdir(baseDir);

        try {
            List<File> files = new ArrayList<>();

            Map<AsccpManifestId, ReleaseId> releaseIdMap = repositoryFactory.releaseQueryRepository(requester)
                    .getReleaseIdMapByAsccpManifestIdList(asccpManifestIdList);
            CcDocument ccDocument = new CcDocumentImpl(requester, repositoryFactory, releaseIdMap.values());
            Map<String, Integer> pathCounter = new ConcurrentHashMap<>();
            List<Exception> exceptions = new ArrayList<>();
            asccpManifestIdList.parallelStream().forEach(asccpManifestId -> {
                try {
                    XMLExportSchemaModuleVisitor visitor = new XMLExportSchemaModuleVisitor(ccDocument);
                    visitor.setBaseDirectory(baseDir);

                    StandaloneExportContextBuilder builder = new StandaloneExportContextBuilder(ccDocument,
                            pathCounter);
                    ExportContext exportContext = builder.build(asccpManifestId);

                    for (SchemaModule schemaModule : exportContext.getSchemaModules()) {
                        schemaModule.visit(visitor);
                        File file = schemaModule.getModuleFile();
                        if (file != null) {
                            files.add(file);
                        }
                    }
                } catch (Exception e) {
                    // logger.warn("Unexpected error occurs while it generates a stand-alone schema
                    // for 'asccp_manifest_id' [" + asccpManifestId + "]", e);
                    exceptions.add(e);
                }
            });

            if (!exceptions.isEmpty()) {
                throw new IllegalStateException(
                        exceptions.stream().map(e -> e.getMessage()).collect(Collectors.joining("\n")));
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

    public List<ExternalChildComponentRecord> getChildComponents(ReleaseId releaseId) {

        List<ExternalChildComponentRecord> childComponents = new ArrayList<ExternalChildComponentRecord>();

        String asccpChildComponentsSql = " with component_chain as "
                + "( with recursive acc_chain as "
                + "( select acc.acc_id, asccp.guid, asccp_manifest.den, acc_manifest_id, based_acc_manifest_id "
                + " from acc_manifest  "
                + " join acc on acc_manifest.acc_id=acc.acc_id and acc.state='Published' "
                + " join asccp_manifest on asccp_manifest.role_of_acc_manifest_id=acc_manifest.acc_manifest_id "
                + " join asccp on asccp.asccp_id=asccp_manifest.asccp_id and asccp.type='Default' "
                + " where acc_manifest.release_id=" + releaseId
                + " union all "
                + " select acc.acc_id, acc_chain.guid, acc_chain.den, acc_manifest.acc_manifest_id, acc_manifest.based_acc_manifest_id "
                + " from acc"
                + " join acc_manifest on acc.acc_id=acc_manifest.acc_id "
                + " join acc_chain on acc_chain.based_acc_manifest_id=acc_manifest.acc_manifest_id ) "
                + " select acc_chain.guid,acc_chain.den "
                + " ,case when group_acc.acc_id is not null then group_asccp.guid else asccp.guid end as child_guid  "
                + " ,case when group_acc.acc_id is not null then group_asccp_manifest.den else asccp_manifest.den end as child_den "
                + " ,case when group_acc.acc_id is not null then group_asccp.property_term else asccp.property_term end as child_property_term "
                + " ,case when group_acc.acc_id is not null then group_asccp.last_update_timestamp else asccp.last_update_timestamp end as child_last_updated_timestamp "
                + " ,case when group_acc.acc_id is not null then group_ascc.cardinality_min else ascc.cardinality_min end as cardinality_min "
                + " ,case when group_acc.acc_id is not null then group_ascc.cardinality_max else ascc.cardinality_max end as cardinality_max "
                + " ,acc_chain.acc_manifest_id,group_acc_manifest.acc_manifest_id as group_acc_manifest_id "
                + " from acc_chain "
                + " join ascc_manifest on acc_chain.acc_manifest_id=ascc_manifest.from_acc_manifest_id "
                + " join ascc on ascc.ascc_id=ascc_manifest.ascc_id "
                + " join asccp_manifest on asccp_manifest.asccp_manifest_id=ascc_manifest.to_asccp_manifest_id "
                + " join asccp on asccp_manifest.asccp_id=asccp.asccp_id "
                + " left join acc_manifest as group_acc_manifest on group_acc_manifest.acc_manifest_id=asccp_manifest.role_of_acc_manifest_id "
                + " left join acc as group_acc on group_acc.acc_id=group_acc_manifest.acc_id and group_acc.oagis_component_type=3 "
                + " left join ascc_manifest as group_ascc_manifest on group_acc_manifest.acc_manifest_id=group_ascc_manifest.from_acc_manifest_id "
                + " left join ascc as group_ascc on group_ascc.ascc_id=group_ascc_manifest.ascc_id "
                + " left join asccp_manifest as group_asccp_manifest on group_asccp_manifest.asccp_manifest_id=group_ascc_manifest.to_asccp_manifest_id "
                + " left join asccp as group_asccp on group_asccp_manifest.asccp_id=group_asccp.asccp_id) "
                + " select 'aggregate' as type, den, guid, child_den, child_property_term, child_last_updated_timestamp, child_guid, cardinality_min, cardinality_max, child_den as data_type"
                + " from component_chain "
                + " where child_guid is not null "
                + " union "
                + " select 'basic' as type, component_chain.den, component_chain.guid, bcc_manifest.den, bccp.property_term, bccp.last_update_timestamp as child_last_updated_timestamp, bcc.guid as child_guid, bcc.cardinality_min, bcc.cardinality_max, dt.data_type_term as data_type"
                + " from component_chain "
                + " join bcc_manifest on bcc_manifest.from_acc_manifest_id=component_chain.acc_manifest_id "
                + " join bccp_manifest on bccp_manifest.bccp_manifest_id=bcc_manifest.to_bccp_manifest_id "
                + " join bccp on bccp.bccp_id=bccp_manifest.bccp_id "
                + " join bcc on bcc.bcc_id=bcc_manifest.bcc_id and bcc.entity_type=1 "
                + " join dt_manifest on dt_manifest.dt_manifest_id=bccp_manifest.bdt_manifest_id "
                + " join dt on dt.dt_id=dt_manifest.dt_id "
                + ";";

        /*
         * Map<String, Result<Record>> componentsByGuid =
         * dslContext.resultQuery(asccpChildComponentsSql)
         * .fetchGroups(field(name("guid"), String.class));
         * 
         * 
         * for (Map.Entry<String, Result<Record>> entry : componentsByGuid.entrySet()) {
         * Guid guid = new Guid(entry.getKey());
         * Result<Record> children = entry.getValue();
         * List<ChildComponentRecord> componentChildComponents = new
         * ArrayList<ChildComponentRecord>();
         * 
         * for (Record childComponentRecord : children) {
         * String type = childComponentRecord.get(field(name("type"), String.class));
         * ChildComponentRecord childComponent = new ChildComponentRecord(
         * childComponentRecord.get(field(name("guid"), String.class)),
         * childComponentRecord.get(field(name("den"), String.class)),
         * childComponentRecord.get(field(name("child_guid"), String.class)),
         * childComponentRecord.get(field(name("child_den"), String.class)),
         * childComponentRecord.get(field(name("child_property_term"), String.class)),
         * (type.equals("basic"))?AssociatedComponentType.BASIC:AssociatedComponentType.
         * SHARED,
         * childComponentRecord.get(field(name("data_type"), String.class)),
         * childComponentRecord.get(field(name("cardinality_min"), Integer.class)),
         * childComponentRecord.get(field(name("cardinality_max"), Integer.class))
         * );
         * 
         * componentChildComponents.add(childComponent);
         * }
         * 
         * childComponents.put(guid, componentChildComponents);
         * }
         */

        dslContext.resultQuery(asccpChildComponentsSql).fetch().forEach(childComponentRecord -> {
            String type = childComponentRecord.get(field(name("type"), String.class));
            ExternalChildComponentRecord childComponent = new ExternalChildComponentRecord(
                    childComponentRecord.get(field(name("guid"), String.class)),
                    childComponentRecord.get(field(name("den"), String.class)),
                    childComponentRecord.get(field(name("child_guid"), String.class)),
                    childComponentRecord.get(field(name("child_den"), String.class)),
                    childComponentRecord.get(field(name("child_property_term"), String.class)),
                    childComponentRecord.get(field(name("child_last_updated_timestamp"), Timestamp.class)),
                    (type.equals("basic")) ? AssociatedComponentType.BASIC : AssociatedComponentType.SHARED,
                    childComponentRecord.get(field(name("data_type"), String.class)),
                    childComponentRecord.get(field(name("cardinality_min"), Integer.class)),
                    childComponentRecord.get(field(name("cardinality_max"), Integer.class)));
            childComponents.add(childComponent);
        });

        return childComponents;

    }

}
