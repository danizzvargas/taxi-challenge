package com.gbm.challenge.taxitracking.webservice;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Available endpoints for web service.
 *
 * @author danizz
 */
@RestController
@Slf4j
public class TrackingRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${webhook.url}")
    private String webhookUrl;

    /**
     * Set a location and send a request to the interested parties.
     *
     * @return HTTP status of the request.
     */
    @PutMapping("/set-location")
    public ResponseEntity setLocation() {
        log.debug("setLocation()");

        sendWebhookRequest(0, 0); // Webhook request.

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Endpoint to get the current tracking.
     *
     * @return HTTP status of the request and JSON data.
     */
    @GetMapping("/get-location")
    public ResponseEntity getLocation() {
        log.debug("getLocation() {}");

        Map response = new HashMap<>();
        response.put("method", "get-location");

        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Endpoint to get the historical tracking.
     *
     * @return HTTP status of the request and JSON data.
     */
    @GetMapping("/get-historical")
    public ResponseEntity getHistorical() {
        log.debug("getHistorical() {}");

        Map response = new HashMap<>();
        response.put("method", "get-historical");

        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Build and send a webhook request.
     *
     * @param lat Latitude.
     * @param lng Longitude.
     */
    private void sendWebhookRequest(float lat, float lng) {

        Map request = new HashMap<>();
        request.put("USER", getUser());
        request.put("TYPE", "New location");
        request.put("LATITUDE", lat);
        request.put("LONGITUDE", lng);

        log.debug("Webhook URL: {}", webhookUrl);
        log.debug("Webhook request: {}", request);

        restTemplate.put(webhookUrl, request);
    }

    /**
     * Get the current user.
     *
     * @return User's name who made the request.
     */
    private String getUser() {
        String usr = SecurityContextHolder.getContext().getAuthentication().getName();

        log.debug("Current user: {}", usr);
        return usr;
    }
}
