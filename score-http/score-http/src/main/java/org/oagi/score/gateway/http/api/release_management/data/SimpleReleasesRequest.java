package org.oagi.score.gateway.http.api.release_management.data;

import org.oagi.score.data.RepositoryRequest;
import org.springframework.security.core.AuthenticatedPrincipal;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class SimpleReleasesRequest extends RepositoryRequest {

    private BigInteger libraryId;

    private List<ReleaseState> states = Collections.emptyList();

    public SimpleReleasesRequest(AuthenticatedPrincipal user) {
        super(user);
    }

    public SimpleReleasesRequest(AuthenticatedPrincipal user, LocalDateTime localDateTime) {
        super(user, localDateTime);
    }

    public BigInteger getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(BigInteger libraryId) {
        this.libraryId = libraryId;
    }

    public List<ReleaseState> getStates() {
        return states;
    }

    public void setStates(List<ReleaseState> states) {
        this.states = states;
    }
}
