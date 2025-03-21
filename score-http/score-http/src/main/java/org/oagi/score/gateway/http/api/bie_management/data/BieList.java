package org.oagi.score.gateway.http.api.bie_management.data;

import lombok.Data;
import org.oagi.score.repo.api.bie.model.BieState;
import org.oagi.score.repo.api.businesscontext.model.BusinessContext;
import org.oagi.score.service.common.data.AccessPrivilege;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class BieList {

    private BigInteger topLevelAsbiepId;
    private String den;
    private String propertyTerm;
    private String displayName;
    private String guid;
    private String releaseNum;
    private List<BusinessContext> businessContexts;
    private String owner;
    private BigInteger ownerUserId;
    private boolean ownerIsDeveloper;
    private boolean ownerIsAdmin;
    private AccessPrivilege access;
    private String version;
    private String status;
    private String bizTerm;
    private String remark;
    private boolean deprecated;
    private String deprecatedReason;
    private String deprecatedRemark;
    private Date lastUpdateTimestamp;
    private String lastUpdateUser;
    private BieState state;

    private BigInteger sourceTopLevelAsbiepId;
    private BigInteger sourceReleaseId;
    private String sourceDen;
    private String sourceDisplayName;
    private String sourceReleaseNum;
    private String sourceAction;
    private Date sourceTimestamp;

    private BigInteger basedTopLevelAsbiepId;
    private BigInteger basedTopLevelAsbiepReleaseId;
    private String basedTopLevelAsbiepReleaseNum;
    private String basedTopLevelAsbiepDen;
    private String basedTopLevelAsbiepDisplayName;

}
