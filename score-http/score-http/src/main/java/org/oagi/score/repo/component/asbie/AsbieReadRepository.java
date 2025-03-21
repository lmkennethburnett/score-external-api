package org.oagi.score.repo.component.asbie;

import org.jooq.DSLContext;
import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.bie_management.data.bie_edit.BieEditUsed;
import org.oagi.score.gateway.http.api.bie_management.data.bie_edit.tree.BieEditRef;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.*;
import org.oagi.score.repo.component.ascc.AsccReadRepository;
import org.oagi.score.repo.component.asccp.AsccpReadRepository;
import org.oagi.score.service.common.data.CcState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.and;
import static org.oagi.score.repo.api.impl.jooq.entity.Tables.*;

@Repository
public class AsbieReadRepository {

    @Autowired
    private DSLContext dslContext;

    @Autowired
    private AsccReadRepository asccReadRepository;

    @Autowired
    private AsccpReadRepository asccpReadRepository;

    private AsbieRecord getAsbieByTopLevelAsbiepIdAndHashPath(BigInteger topLevelAsbiepId, String hashPath) {
        return dslContext.selectFrom(ASBIE)
                .where(and(
                        ASBIE.OWNER_TOP_LEVEL_ASBIEP_ID.eq(ULong.valueOf(topLevelAsbiepId)),
                        ASBIE.HASH_PATH.eq(hashPath)
                ))
                .fetchOptional().orElse(null);
    }

    public AsbieNode getAsbieNode(BigInteger topLevelAsbiepId, BigInteger asccManifestId, String hashPath) {
        AsccManifestRecord asccManifestRecord = asccReadRepository.getAsccManifestById(asccManifestId);
        AsccRecord asccRecord = asccReadRepository.getAsccByManifestId(asccManifestId);

        AsccpRecord asccpRecord = asccpReadRepository.getAsccpByManifestId(asccManifestRecord.getToAsccpManifestId().toBigInteger());
        if (asccRecord == null) {
            return null;
        }

        AsbieNode asbieNode = new AsbieNode();

        AsbieNode.Ascc ascc = asbieNode.getAscc();
        ascc.setAsccManifestId(asccManifestId);
        ascc.setGuid(asccRecord.getGuid());
        ascc.setCardinalityMin(asccRecord.getCardinalityMin());
        ascc.setCardinalityMax(asccRecord.getCardinalityMax());
        String den = dslContext.select(ASCC_MANIFEST.DEN)
                .from(ASCC_MANIFEST)
                .where(ASCC_MANIFEST.ASCC_MANIFEST_ID.eq(ULong.valueOf(asccManifestId)))
                .fetchOneInto(String.class);
        ascc.setDen(den);
        ascc.setDefinition(asccRecord.getDefinition());
        ascc.setState(CcState.valueOf(asccRecord.getState()));

        AsbieNode.Asbie asbie = getAsbie(topLevelAsbiepId, hashPath);
        asbieNode.setAsbie(asbie);

        if (asbie.getAsbieId() == null) {
            asbie.setBasedAsccManifestId(ascc.getAsccManifestId());
            asbie.setCardinalityMin(ascc.getCardinalityMin());
            asbie.setCardinalityMax(ascc.getCardinalityMax());
            asbie.setNillable(asccpRecord.getIsNillable() == 1);
        }

        return asbieNode;
    }

    public AsbieNode getAsbieNode(BigInteger asbieId) {
        AsbieRecord asbieRecord = dslContext.selectFrom(ASBIE)
                .where(ASBIE.ASBIE_ID.eq(ULong.valueOf(asbieId)))
                .fetchOne();
        return getAsbieNode(asbieRecord.getOwnerTopLevelAsbiepId().toBigInteger(),
                asbieRecord.getBasedAsccManifestId().toBigInteger(), asbieRecord.getHashPath());
    }

    public AsbieNode.Asbie getAsbie(BigInteger topLevelAsbiepId, String hashPath) {
        AsbieNode.Asbie asbie = new AsbieNode.Asbie();
        asbie.setHashPath(hashPath);

        AsbieRecord asbieRecord = getAsbieByTopLevelAsbiepIdAndHashPath(topLevelAsbiepId, hashPath);
        if (asbieRecord != null) {
            asbie.setOwnerTopLevelAsbiepId(asbieRecord.getOwnerTopLevelAsbiepId().toBigInteger());
            asbie.setAsbieId(asbieRecord.getAsbieId().toBigInteger());
            asbie.setToAsbiepId(asbieRecord.getToAsbiepId().toBigInteger());
            asbie.setGuid(asbieRecord.getGuid());
            asbie.setFromAbieHashPath(dslContext.select(ABIE.HASH_PATH)
                    .from(ABIE)
                    .where(and(
                            ABIE.OWNER_TOP_LEVEL_ASBIEP_ID.eq(ULong.valueOf(topLevelAsbiepId)),
                            ABIE.ABIE_ID.eq(asbieRecord.getFromAbieId())
                    ))
                    .fetchOneInto(String.class));
            asbie.setToAsbiepHashPath(dslContext.select(ASBIEP.HASH_PATH)
                    .from(ASBIEP)
                    .where(and(
                            ASBIEP.OWNER_TOP_LEVEL_ASBIEP_ID.eq(ULong.valueOf(topLevelAsbiepId)),
                            ASBIEP.ASBIEP_ID.eq(asbieRecord.getToAsbiepId())
                    ))
                    .fetchOneInto(String.class));
            asbie.setBasedAsccManifestId(asbieRecord.getBasedAsccManifestId().toBigInteger());
            asbie.setUsed(asbieRecord.getIsUsed() == 1);
            asbie.setCardinalityMin(asbieRecord.getCardinalityMin());
            asbie.setCardinalityMax(asbieRecord.getCardinalityMax());
            asbie.setNillable(asbieRecord.getIsNillable() == 1);
            asbie.setRemark(asbieRecord.getRemark());
            asbie.setDefinition(asbieRecord.getDefinition());
            asbie.setDeprecated(asbieRecord.getIsDeprecated() == 1);
        }

        return asbie;
    }

    public List<BieEditUsed> getUsedAsbieList(BigInteger topLevelAsbiepId) {
        return dslContext.select(ASBIE.IS_USED, ASBIE.ASBIE_ID, ASBIE.BASED_ASCC_MANIFEST_ID,
                        ASBIE.HASH_PATH, ASBIE.OWNER_TOP_LEVEL_ASBIEP_ID,
                        ASBIEP.DISPLAY_NAME,
                        ASBIE.CARDINALITY_MIN, ASBIE.CARDINALITY_MAX,
                        ASBIE.IS_DEPRECATED)
                .from(ASBIE)
                .join(ASBIEP).on(ASBIE.TO_ASBIEP_ID.eq(ASBIEP.ASBIEP_ID))
                .where(ASBIE.OWNER_TOP_LEVEL_ASBIEP_ID.eq(ULong.valueOf(topLevelAsbiepId)))
                .fetchStream().map(record -> {
                    BieEditUsed bieEditUsed = new BieEditUsed();
                    bieEditUsed.setUsed(record.get(ASBIE.IS_USED) == 1);
                    bieEditUsed.setType("ASBIE");
                    bieEditUsed.setBieId(record.get(ASBIE.ASBIE_ID).toBigInteger());
                    bieEditUsed.setManifestId(record.get(ASBIE.BASED_ASCC_MANIFEST_ID).toBigInteger());
                    bieEditUsed.setHashPath(record.get(ASBIE.HASH_PATH));
                    bieEditUsed.setOwnerTopLevelAsbiepId(record.get(ASBIE.OWNER_TOP_LEVEL_ASBIEP_ID).toBigInteger());
                    bieEditUsed.setDisplayName(record.get(ASBIEP.DISPLAY_NAME));
                    bieEditUsed.setCardinalityMin(record.get(ASBIE.CARDINALITY_MIN));
                    bieEditUsed.setCardinalityMax(record.get(ASBIE.CARDINALITY_MAX));
                    bieEditUsed.setDeprecated(record.get(ASBIE.IS_DEPRECATED) == 1);
                    return bieEditUsed;
                }).collect(Collectors.toList());
    }

    public List<BieEditRef> getBieRefList(BigInteger topLevelAsbiepId) {
        if (topLevelAsbiepId == null || topLevelAsbiepId.longValue() <= 0L) {
            return Collections.emptyList();
        }

        List<BieEditRef> bieEditRefList = new ArrayList();
        List<BieEditRef> refTopLevelAsbiepIdList = getRefTopLevelAsbiepIdList(topLevelAsbiepId);
        bieEditRefList.addAll(refTopLevelAsbiepIdList);

        if (!bieEditRefList.isEmpty()) {
            refTopLevelAsbiepIdList.stream().map(e -> e.getRefTopLevelAsbiepId()).distinct().forEach(refTopLevelAsbiepId -> {
                bieEditRefList.addAll(getBieRefList(refTopLevelAsbiepId));
            });
        }
        return bieEditRefList;
    }

    private List<BieEditRef> getRefTopLevelAsbiepIdList(BigInteger topLevelAsbiepId) {
        return dslContext.select(
                ASBIE.ASBIE_ID,
                ASBIE.HASH_PATH,
                ASBIE.BASED_ASCC_MANIFEST_ID,
                ASBIE.OWNER_TOP_LEVEL_ASBIEP_ID.as("top_level_asbiep_id"),
                TOP_LEVEL_ASBIEP.as("asbie_top_level_asbiep").BASED_TOP_LEVEL_ASBIEP_ID.as("based_top_level_asbiep_id"),
                ASBIEP.OWNER_TOP_LEVEL_ASBIEP_ID.as("ref_top_level_asbiep_id"),
                TOP_LEVEL_ASBIEP.as("asbiep_top_level_asbiep").BASED_TOP_LEVEL_ASBIEP_ID.as("ref_based_top_level_asbiep_id"),
                TOP_LEVEL_ASBIEP.as("asbiep_top_level_asbiep").INVERSE_MODE)
                .from(ASBIE)
                .join(ASBIEP).on(
                        and(ASBIE.TO_ASBIEP_ID.eq(ASBIEP.ASBIEP_ID),
                            ASBIEP.OWNER_TOP_LEVEL_ASBIEP_ID.notEqual(ASBIE.OWNER_TOP_LEVEL_ASBIEP_ID)))
                .join(TOP_LEVEL_ASBIEP.as("asbie_top_level_asbiep"))
                .on(ASBIE.OWNER_TOP_LEVEL_ASBIEP_ID.eq(TOP_LEVEL_ASBIEP.as("asbie_top_level_asbiep").TOP_LEVEL_ASBIEP_ID))
                .join(TOP_LEVEL_ASBIEP.as("asbiep_top_level_asbiep"))
                .on(ASBIEP.OWNER_TOP_LEVEL_ASBIEP_ID.eq(TOP_LEVEL_ASBIEP.as("asbiep_top_level_asbiep").TOP_LEVEL_ASBIEP_ID))
                .where(ASBIE.OWNER_TOP_LEVEL_ASBIEP_ID.eq(ULong.valueOf(topLevelAsbiepId)))
                .fetch(record -> {
                    BieEditRef bieEditRef = new BieEditRef();
                    bieEditRef.setAsbieId(record.get(ASBIE.ASBIE_ID).toBigInteger());
                    bieEditRef.setBasedAsccManifestId(record.get(ASBIE.BASED_ASCC_MANIFEST_ID).toBigInteger());
                    bieEditRef.setHashPath(record.get(ASBIE.HASH_PATH));
                    bieEditRef.setTopLevelAsbiepId(record.get(ASBIE.OWNER_TOP_LEVEL_ASBIEP_ID.as("top_level_asbiep_id")).toBigInteger());
                    ULong basedTopLevelAsbiepId = record.get(TOP_LEVEL_ASBIEP.as("asbie_top_level_asbiep").BASED_TOP_LEVEL_ASBIEP_ID.as("based_top_level_asbiep_id"));
                    if (basedTopLevelAsbiepId != null) {
                        bieEditRef.setBasedTopLevelAsbiepId(basedTopLevelAsbiepId.toBigInteger());
                    }
                    bieEditRef.setRefTopLevelAsbiepId(record.get(ASBIEP.OWNER_TOP_LEVEL_ASBIEP_ID.as("ref_top_level_asbiep_id")).toBigInteger());
                    ULong refBasedTopLevelAsbiepId = record.get(TOP_LEVEL_ASBIEP.as("asbiep_top_level_asbiep").BASED_TOP_LEVEL_ASBIEP_ID.as("ref_based_top_level_asbiep_id"));
                    if (refBasedTopLevelAsbiepId != null) {
                        bieEditRef.setRefBasedTopLevelAsbiepId(refBasedTopLevelAsbiepId.toBigInteger());
                    }
                    bieEditRef.setRefInverseMode(record.get(TOP_LEVEL_ASBIEP.INVERSE_MODE) == 1);
                    return bieEditRef;
                });
    }
}
