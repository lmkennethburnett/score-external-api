package org.oagi.score.repo.api.agency.model;

import org.oagi.score.repo.api.base.PaginationRequest;
import org.oagi.score.repo.api.corecomponent.model.CcState;
import org.oagi.score.repo.api.user.model.ScoreUser;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GetAgencyIdListListRequest extends PaginationRequest<AgencyIdList> {
    public GetAgencyIdListListRequest(ScoreUser requester) {
        super(requester, AgencyIdList.class);
    }

    private BigInteger libraryId;
    private BigInteger releaseId;

    private String name;
    private String definition;
    private String module;

    private Boolean deprecated;
    private Boolean newComponent;
    private List<BigInteger> namespaces;
    private Collection<String> updaterUsernameList;
    private LocalDateTime updateStartDate;
    private LocalDateTime updateEndDate;
    private List<CcState> states;
    private List<String> ownerLoginIds;
    private List<String> updaterLoginIds;

    public BigInteger getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(BigInteger libraryId) {
        this.libraryId = libraryId;
    }

    public BigInteger getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(BigInteger releaseId) {
        this.releaseId = releaseId;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Boolean getNewComponent() {
        return newComponent;
    }

    public void setNewComponent(Boolean newComponent) {
        this.newComponent = newComponent;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public List<CcState> getStates() {
        return states;
    }

    public void setStates(List<CcState> states) {
        this.states = states;
    }

    public List<BigInteger> getNamespaces() {
        return (namespaces != null) ? namespaces : Collections.emptyList();
    }

    public void setNamespaces(List<BigInteger> namespaces) {
        this.namespaces = namespaces;
    }

    public List<String> getOwnerLoginIds() {
        return ownerLoginIds;
    }

    public void setOwnerLoginIds(List<String> ownerLoginIds) {
        this.ownerLoginIds = ownerLoginIds;
    }

    public List<String> getUpdaterLoginIds() {
        return updaterLoginIds;
    }

    public void setUpdaterLoginIds(List<String> updaterLoginIds) {
        this.updaterLoginIds = updaterLoginIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Collection<String> getUpdaterUsernameList() {
        return Objects.requireNonNullElse(updaterUsernameList, Collections.emptyList());
    }

    public void setUpdaterUsernameList(Collection<String> updaterUsernameList) {
        this.updaterUsernameList = updaterUsernameList;
    }

    public LocalDateTime getUpdateStartDate() {
        return updateStartDate;
    }

    public void setUpdateStartDate(LocalDateTime updateStartDate) {
        this.updateStartDate = updateStartDate;
    }

    public LocalDateTime getUpdateEndDate() {
        return updateEndDate;
    }

    public void setUpdateEndDate(LocalDateTime updateEndDate) {
        this.updateEndDate = updateEndDate;
    }
}
