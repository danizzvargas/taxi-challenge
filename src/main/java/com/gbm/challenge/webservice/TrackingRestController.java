package com.gbm.challenge.webservice;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Available endpoints for web service.
 *
 * @author danizz
 */
@RestController
@Slf4j
public class TrackingRestController {

    /**
     * Endpoint to update some position.
     *
     * @return HTTP status of the request.
     */
    @GetMapping("/update-location")
    public ResponseEntity updateLocation() {
        log.debug("updateLocation() {}");
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

}
