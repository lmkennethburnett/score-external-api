package org.oagi.score.gateway.http.api.bie_management.controller;

import org.oagi.score.data.BizCtx;
import org.oagi.score.gateway.http.api.bie_management.data.*;
import org.oagi.score.gateway.http.api.bie_management.service.BieService;
import org.oagi.score.gateway.http.api.business_term_management.data.AsbieListRecord;
import org.oagi.score.gateway.http.api.context_management.data.BizCtxAssignment;
import org.oagi.score.gateway.http.api.mail.data.SendMailRequest;
import org.oagi.score.gateway.http.api.mail.service.MailService;
import org.oagi.score.gateway.http.configuration.security.SessionService;
import org.oagi.score.repo.api.base.SortDirection;
import org.oagi.score.repo.api.bie.model.BieState;
import org.oagi.score.repo.api.businessterm.model.ConfirmAsbieBbieListRequest;
import org.oagi.score.service.common.data.AccessPrivilege;
import org.oagi.score.service.common.data.PageRequest;
import org.oagi.score.service.common.data.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.oagi.score.repo.api.impl.utils.StringUtils.hasLength;

@RestController
public class BieListController {

    @Autowired
    private BieService bieService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/bie_list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<BieList> getBieList(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                            @RequestParam(name = "libraryId") BigInteger libraryId,
                                            @RequestParam(name = "den", required = false) String den,
                                            @RequestParam(name = "propertyTerm", required = false) String propertyTerm,
                                            @RequestParam(name = "businessContext", required = false) String businessContext,
                                            @RequestParam(name = "version", required = false) String version,
                                            @RequestParam(name = "remark", required = false) String remark,
                                            @RequestParam(name = "asccpManifestId", required = false) BigInteger asccpManifestId,
                                            @RequestParam(name = "access", required = false) String access,
                                            @RequestParam(name = "states", required = false) String states,
                                            @RequestParam(name = "deprecated", required = false) String deprecated,
                                            @RequestParam(name = "excludePropertyTerms", required = false) String excludePropertyTerms,
                                            @RequestParam(name = "topLevelAsbiepIds", required = false) String topLevelAsbiepIds,
                                            @RequestParam(name = "basedTopLevelAsbiepIds", required = false) String basedTopLevelAsbiepIds,
                                            @RequestParam(name = "excludeTopLevelAsbiepIds", required = false) String excludeTopLevelAsbiepIds,
                                            @RequestParam(name = "ownerLoginIds", required = false) String ownerLoginIds,
                                            @RequestParam(name = "updaterLoginIds", required = false) String updaterLoginIds,
                                            @RequestParam(name = "updateStart", required = false) String updateStart,
                                            @RequestParam(name = "updateEnd", required = false) String updateEnd,
                                            @RequestParam(name = "ownedByDeveloper", required = false) Boolean ownedByDeveloper,
                                            @RequestParam(name = "releaseIds", required = false) String releaseIds,
                                            @RequestParam(name = "sortActive", required = false) String sortActive, // Deprecated. Use 'sortActives' instead of 'sortActive'.
                                            @RequestParam(name = "sortDirection", required = false) String sortDirection, // Deprecated. Use 'sortDirections' instead of 'sortDirection'.
                                            @RequestParam(name = "sortActives", required = false) String sortActives,
                                            @RequestParam(name = "sortDirections", required = false) String sortDirections,
                                            @RequestParam(name = "pageIndex") int pageIndex,
                                            @RequestParam(name = "pageSize") int pageSize) {

        BieListRequest request = new BieListRequest();

        request.setLibraryId(libraryId);
        request.setDen(den);
        request.setPropertyTerm(propertyTerm);
        request.setBusinessContext(businessContext);
        request.setVersion(version);
        request.setRemark(remark);
        request.setAsccpManifestId(asccpManifestId);
        request.setAccess(StringUtils.hasLength(access) ? AccessPrivilege.valueOf(access) : null);
        request.setStates(StringUtils.hasLength(states) ?
                Arrays.asList(states.split(",")).stream()
                        .map(e -> BieState.valueOf(e)).collect(Collectors.toList()) : Collections.emptyList());
        if (StringUtils.hasLength(deprecated)) {
            if ("true".equalsIgnoreCase(deprecated.toLowerCase())) {
                request.setDeprecated(true);
            } else if ("false".equalsIgnoreCase(deprecated.toLowerCase())) {
                request.setDeprecated(false);
            }
        }
        request.setExcludePropertyTerms(!StringUtils.hasLength(excludePropertyTerms) ? Collections.emptyList() :
                Arrays.asList(excludePropertyTerms.split(",")).stream().map(e -> e.trim()).filter(e -> StringUtils.hasLength(e)).collect(Collectors.toList()));
        request.setTopLevelAsbiepIds(!StringUtils.hasLength(topLevelAsbiepIds) ? Collections.emptyList() :
                Arrays.asList(topLevelAsbiepIds.split(",")).stream().map(e -> e.trim()).filter(e -> StringUtils.hasLength(e)).map(e -> new BigInteger(e)).collect(Collectors.toList()));
        request.setBasedTopLevelAsbiepIds(!StringUtils.hasLength(basedTopLevelAsbiepIds) ? Collections.emptyList() :
                Arrays.asList(basedTopLevelAsbiepIds.split(",")).stream().map(e -> e.trim()).filter(e -> StringUtils.hasLength(e)).map(e -> new BigInteger(e)).collect(Collectors.toList()));
        request.setExcludeTopLevelAsbiepIds(!StringUtils.hasLength(excludeTopLevelAsbiepIds) ? Collections.emptyList() :
                Arrays.asList(excludeTopLevelAsbiepIds.split(",")).stream().map(e -> e.trim()).filter(e -> StringUtils.hasLength(e)).map(e -> new BigInteger(e)).collect(Collectors.toList()));
        request.setOwnerLoginIds(!StringUtils.hasLength(ownerLoginIds) ? Collections.emptyList() :
                Arrays.asList(ownerLoginIds.split(",")).stream().map(e -> e.trim()).filter(e -> StringUtils.hasLength(e)).collect(Collectors.toList()));
        request.setUpdaterLoginIds(!StringUtils.hasLength(updaterLoginIds) ? Collections.emptyList() :
                Arrays.asList(updaterLoginIds.split(",")).stream().map(e -> e.trim()).filter(e -> StringUtils.hasLength(e)).collect(Collectors.toList()));
        request.setOwnedByDeveloper(ownedByDeveloper);
        request.setReleaseIds(!StringUtils.hasLength(releaseIds) ? Collections.emptyList() :
                Arrays.asList(releaseIds.split(",")).stream().map(e -> e.trim()).filter(e -> StringUtils.hasLength(e)).map(e -> new BigInteger(e)).collect(Collectors.toList()));

        if (StringUtils.hasLength(updateStart)) {
            request.setUpdateStartDate(new Date(Long.valueOf(updateStart)));
        }
        if (StringUtils.hasLength(updateEnd)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.valueOf(updateEnd));
            calendar.add(Calendar.DATE, 1);
            request.setUpdateEndDate(calendar.getTime());
        }

        PageRequest pageRequest = new PageRequest();
        if (hasLength(sortActive)) {
            pageRequest.setSortActive(sortActive);
        } else {
            pageRequest.setSortActives(!hasLength(sortActives) ? Collections.emptyList() :
                    Arrays.asList(sortActives.split(",")).stream().map(e -> e.trim()).filter(e -> hasLength(e)).collect(Collectors.toList()));
        }
        if (hasLength(sortDirection)) {
            pageRequest.setSortDirection(sortDirection);
        } else {
            pageRequest.setSortDirections(!hasLength(sortDirections) ? Collections.emptyList() :
                    Arrays.asList(sortDirections.split(",")).stream().map(e -> e.trim()).filter(e -> hasLength(e)).map(e -> SortDirection.valueOf(e.toUpperCase())).collect(Collectors.toList()));
        }

        pageRequest.setPageIndex(pageIndex);
        pageRequest.setPageSize(pageSize);
        request.setPageRequest(pageRequest);
        return bieService.getBieList(user, request);
    }

    @RequestMapping(value = "/bie_list/{topLevelAsbiepId:[\\d]+}/usage",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<BieList> getBieUsageList(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                                 @PathVariable("topLevelAsbiepId") BigInteger topLevelAsbiepId,
                                                 @RequestParam(name = "sortActive") String sortActive,
                                                 @RequestParam(name = "sortDirection") String sortDirection,
                                                 @RequestParam(name = "pageIndex") int pageIndex,
                                                 @RequestParam(name = "pageSize") int pageSize) {

        BieListRequest request = new BieListRequest();
        request.setUsageTopLevelAsbiepId(topLevelAsbiepId);
        PageRequest pageRequest = new PageRequest();
        pageRequest.setSortActive(sortActive);
        pageRequest.setSortDirection(sortDirection);
        pageRequest.setPageIndex(pageIndex);
        pageRequest.setPageSize(pageSize);
        request.setPageRequest(pageRequest);
        return bieService.getUsageOfBieList(user, request);
    }

    @RequestMapping(value = "/bie_list/asbie_bbie",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<AsbieListRecord> getAsbieAndBbieList(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                                             @RequestParam(name = "libraryId") BigInteger libraryId,
                                                             @RequestParam(name = "topLevelAsccpPropertyTerm", required = false) String topLevelAsccpPropertyTerm,
                                                             @RequestParam(name = "ownerLoginIds", required = false) String ownerLoginIds,
                                                             @RequestParam(name = "updaterLoginIds", required = false) String updaterLoginIds,
                                                             @RequestParam(name = "businessContext", required = false) String businessContext,
                                                             @RequestParam(name = "version", required = false) String version,
                                                             @RequestParam(name = "remark", required = false) String remark,
                                                             @RequestParam(name = "den", required = false) String den,
                                                             @RequestParam(name = "types", required = false) String types,
                                                             @RequestParam(name = "access", required = false) String access,
                                                             @RequestParam(name = "states", required = false) String states,
                                                             @RequestParam(name = "bieId", required = false) BigInteger bieId,
                                                             @RequestParam(name = "updateStart", required = false) String updateStart,
                                                             @RequestParam(name = "updateEnd", required = false) String updateEnd,
                                                             @RequestParam(name = "ownedByDeveloper", required = false) Boolean ownedByDeveloper,
                                                             @RequestParam(name = "releaseIds", required = false) String releaseIds,
                                                             @RequestParam(name = "sortActive") String sortActive,
                                                             @RequestParam(name = "sortDirection") String sortDirection,
                                                             @RequestParam(name = "pageIndex") int pageIndex,
                                                             @RequestParam(name = "pageSize") int pageSize) {

        BieListRequest request = new BieListRequest();
        PageRequest pageRequest = new PageRequest();

        request.setLibraryId(libraryId);
        request.setPropertyTerm(topLevelAsccpPropertyTerm);
        request.setBusinessContext(businessContext);
        request.setVersion(version);
        request.setRemark(remark);
        request.setAsccBccDen(den);
        request.setBieId(bieId);
        request.setTypes(StringUtils.hasLength(types) ? Arrays.asList(types.split(",")) : Collections.emptyList());
        request.setAccess(StringUtils.hasLength(access) ? AccessPrivilege.valueOf(access) : null);
        request.setStates(StringUtils.hasLength(states) ?
                Arrays.asList(states.split(",")).stream()
                        .map(e -> BieState.valueOf(e)).collect(Collectors.toList()) : Collections.emptyList());
        request.setOwnerLoginIds(!StringUtils.hasLength(ownerLoginIds) ? Collections.emptyList() :
                Arrays.asList(ownerLoginIds.split(",")).stream().map(e -> e.trim()).filter(e -> StringUtils.hasLength(e)).collect(Collectors.toList()));
        request.setUpdaterLoginIds(!StringUtils.hasLength(updaterLoginIds) ? Collections.emptyList() :
                Arrays.asList(updaterLoginIds.split(",")).stream().map(e -> e.trim()).filter(e -> StringUtils.hasLength(e)).collect(Collectors.toList()));
        request.setOwnedByDeveloper(ownedByDeveloper);
        request.setReleaseIds(!StringUtils.hasLength(releaseIds) ? Collections.emptyList() :
                Arrays.asList(releaseIds.split(",")).stream().map(e -> e.trim()).filter(e -> StringUtils.hasLength(e)).map(e -> new BigInteger(e)).collect(Collectors.toList()));

        if (StringUtils.hasLength(updateStart)) {
            request.setUpdateStartDate(new Date(Long.valueOf(updateStart)));
        }
        if (StringUtils.hasLength(updateEnd)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.valueOf(updateEnd));
            calendar.add(Calendar.DATE, 1);
            request.setUpdateEndDate(calendar.getTime());
        }

        pageRequest.setSortActive(sortActive);
        pageRequest.setSortDirection(sortDirection);
        pageRequest.setPageIndex(pageIndex);
        pageRequest.setPageSize(pageSize);
        request.setPageRequest(pageRequest);
        return bieService.getAsbieAndBbieList(user, request);
    }

    @RequestMapping(value = "/bie_list/asbie_bbie/confirm",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<AsbieListRecord> getAsbieAndBbieList(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                                             @RequestBody ConfirmAsbieBbieListRequest request) {
        PageRequest pageRequest = new PageRequest();
        List<AsbieListRecord> records = request.getBiesToAssign().stream().map(bieToAssign -> {
            BieListRequest bieListRequest = new BieListRequest();
            bieListRequest.setBieId(bieToAssign.getBieId());
            bieListRequest.setTypes(List.of(bieToAssign.getBieType()));
            bieListRequest.setPageRequest(pageRequest);
            return bieService.getAsbieAndBbieList(user, bieListRequest).getList().get(0);
        }).collect(Collectors.toList());

        PageResponse<AsbieListRecord> response = new PageResponse();
        response.setList(records);
        response.setPage(pageRequest.getPageIndex());
        response.setSize(pageRequest.getPageSize());
        response.setLength(records.size());
        return response;
    }


    @RequestMapping(value = "/profile_bie_list/delete", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBieList(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                        @RequestBody DeleteBieListRequest request) {
        List<BigInteger> topLevelAsbiepIds = request.getTopLevelAsbiepIds();
        bieService.deleteBieList(sessionService.asScoreUser(user), topLevelAsbiepIds);

        for (BigInteger topLevelAsbiepId : topLevelAsbiepIds) {
            BieEvent event = new BieEvent();
            event.setAction("Discard");
            event.setTopLevelAsbiepId(topLevelAsbiepId);
            event.addProperty("actor", user.getName());
            event.addProperty("timestamp", LocalDateTime.now());
            bieService.fireBieEvent(event);
        }

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/profile_bie/business_ctx_from_abie/{id:[\\d]+}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BizCtx findBizCtxFromAbieId(@PathVariable("id") BigInteger abieId) {
        return bieService.findBizCtxByAbieId(abieId);
    }

    @RequestMapping(value = "/profile_bie/{id:[\\d]+}/biz_ctx", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BizCtxAssignment> getAssignBizCtx(@PathVariable("id") BigInteger topLevelAsbiepId) {
        return bieService.getAssignBizCtx(topLevelAsbiepId);
    }

    @RequestMapping(value = "/profile_bie/{id:[\\d]+}/assign_biz_ctx", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity assignBizCtx(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                       @PathVariable("id") BigInteger topLevelAsbiepId,
                                       @RequestBody Map<String, List<Long>> request) {
        bieService.assignBizCtx(user, topLevelAsbiepId, request.getOrDefault("bizCtxList", Collections.emptyList()));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/profile_bie/{id:[\\d]+}/transfer_ownership", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity transferOwnership(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                            @PathVariable("id") BigInteger topLevelAsbiepId,
                                            @RequestParam(value = "sendNotification", required = false) Boolean sendNotification,
                                            @RequestBody Map<String, Object> requestBody) {
        String targetLoginId = (String) requestBody.get("targetLoginId");
        bieService.transferOwnership(user, topLevelAsbiepId, targetLoginId);

        BieEvent event = new BieEvent();
        event.setAction("UpdateOwnership");
        event.setTopLevelAsbiepId(topLevelAsbiepId);
        event.addProperty("actor", user.getName());
        event.addProperty("target", targetLoginId);
        event.addProperty("timestamp", LocalDateTime.now());
        bieService.fireBieEvent(event);

        if (sendNotification != null && sendNotification) {
            SendMailRequest sendMailRequest = new SendMailRequest();
            sendMailRequest.setRecipient(sessionService.getScoreUserByUsername(targetLoginId));
            sendMailRequest.setTemplateName("bie-ownership-transfer-acceptance");
            sendMailRequest.setParameters((Map<String, Object>) requestBody.get("parameters"));
            mailService.sendMail(sessionService.asScoreUser(user), sendMailRequest);
        }

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/bie_list/state/multiple",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCcState(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                        @RequestBody BieUpdateStateListRequest request) {
        bieService.updateStateBieList(user, request);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/bie_list/transfer_ownership/multiple",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity transferOwnership(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                            @RequestBody BieTransferOwnershipListRequest request) {
        bieService.transferOwnershipList(user, request);

        request.getTopLevelAsbiepIds().forEach(topLevelAsbiepId -> {
            BieEvent event = new BieEvent();
            event.setAction("UpdateOwnership");
            event.setTopLevelAsbiepId(topLevelAsbiepId);
            event.addProperty("actor", user.getName());
            event.addProperty("target", request.getTargetLoginId());
            event.addProperty("timestamp", LocalDateTime.now());
            bieService.fireBieEvent(event);
        });

        return ResponseEntity.noContent().build();
    }

}
