package org.oagi.score.gateway.http.api.application_management.controller;

import org.oagi.score.gateway.http.api.application_management.data.ApplicationConfigurationChangeRequest;
import org.oagi.score.gateway.http.api.application_management.service.ApplicationConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationConfigurationController {

    @Autowired
    private ApplicationConfigurationService service;

    @RequestMapping(value = "/application/{key}", method = RequestMethod.POST)
    public ResponseEntity updateConfiguration(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                              @PathVariable("key") String key,
                                              @RequestBody ApplicationConfigurationChangeRequest request) {
        request.setKey(key);
        service.changeApplicationConfiguration(user, request);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/application/{type}/enable", method = RequestMethod.POST)
    public ResponseEntity tenantEnable(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                       @PathVariable("type") String type) {
        ApplicationConfigurationChangeRequest request = new ApplicationConfigurationChangeRequest();
        switch (type) {
            case "tenant":
                request.setTenantEnabled(true);
                // Multi-tenant mode does not support the business term management.
                request.setBusinessTermEnabled(false);
                break;

            case "business-term":
                request.setBusinessTermEnabled(true);
                break;

            case "bie-inverse-mode":
                request.setBieInverseModeEnabled(true);
                break;

            default:
                throw new UnsupportedOperationException("Unregistered type: " + type);
        }

        service.changeApplicationConfiguration(user, request);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/application/{type}/disable", method = RequestMethod.POST)
    public ResponseEntity tenantDisable(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                        @PathVariable("type") String type) {
        ApplicationConfigurationChangeRequest request = new ApplicationConfigurationChangeRequest();
        switch (type) {
            case "tenant":
                request.setTenantEnabled(false);
                break;

            case "business-term":
                request.setBusinessTermEnabled(false);
                break;

            case "bie-inverse-mode":
                request.setBieInverseModeEnabled(false);
                break;

            default:
                throw new UnsupportedOperationException("Unregistered type: " + type);
        }

        service.changeApplicationConfiguration(user, request);

        return ResponseEntity.noContent().build();
    }

}
