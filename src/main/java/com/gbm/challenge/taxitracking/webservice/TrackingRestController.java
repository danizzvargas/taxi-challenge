package com.gbm.challenge.taxitracking.webservice;

import com.gbm.challenge.taxitracking.entity.TaxiUser;
import com.gbm.challenge.taxitracking.entity.Tracking;
import com.gbm.challenge.taxitracking.entity.Travel;
import com.gbm.challenge.taxitracking.repository.TaxiUserRepository;
import com.gbm.challenge.taxitracking.repository.TrackingRepository;
import com.gbm.challenge.taxitracking.repository.TravelRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private TaxiUserRepository taxiUserRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private TrackingRepository trackingRepository;

    @Value("${webhook.url}")
    private String webhookUrl;

    /**
     * Endpoint to get the last location (if it exists).
     *
     * @return HTTP status of the request and JSON data.
     */
    @GetMapping("/get-last-location")
    public ResponseEntity getLastLocation() {
        log.debug("getLastLocation() {}");

        List<Tracking> lastLocation = trackingRepository.findLastLocation(
                getUser(), PageRequest.of(0, 1, Sort.Direction.DESC, "time"));

        Map response = new HashMap<>();

        if (!lastLocation.isEmpty()) {
            response.put("time", lastLocation.get(0).getTime());
            response.put("latitude", lastLocation.get(0).getLat());
            response.put("longitude", lastLocation.get(0).getLng());
        }

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

        TaxiUser taxiUser = taxiUserRepository.findByPhone(getUser());
        response.put("first_name", taxiUser.getFirstName());
        response.put("last_name", taxiUser.getLastName());
        response.put("phone", taxiUser.getPhone());

        List travelList = new ArrayList<>();
        for (Travel travel : taxiUser.getTravelList()) {
            Map travelMap = new HashMap<>();

            travelMap.put("id", travel.getIdTravel());

            List locationList = new ArrayList<>();
            for (Tracking tracking : travel.getTrackingList()) {
                Map locationMap = new HashMap<>();

                locationMap.put("latitude", tracking.getLat());
                locationMap.put("longitude", tracking.getLng());
                locationMap.put("time", tracking.getTime());

                locationList.add(locationMap);
            }
            travelMap.put("locations", locationList);

            travelList.add(0, travelMap);
        }
        response.put("travels", travelList);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Start a new tracking.
     *
     * @return ID of the new tracking.
     */
    @GetMapping("/new-tracking")
    public ResponseEntity newTracking() {
        log.debug("newTravel()");

        Travel travel = new Travel();
        travel.setIdTaxiUser(taxiUserRepository.findByPhone(getUser()));
        travelRepository.save(travel);
        log.debug("New travel: {}", travel);

        return new ResponseEntity(travel.getIdTravel(), HttpStatus.OK);
    }

    /**
     * Set a location and send a request to the interested parties.
     *
     * @param location
     * @return HTTP status of the request.
     */
    @PutMapping("/set-location")
    public ResponseEntity setLocation(@RequestBody Map location) {
        log.debug("setLocation()");
        log.debug("location received {}", location);

        float lat = Float.valueOf(location.get("lat").toString());
        float lng = Float.valueOf(location.get("lng").toString());

        Tracking tracking = new Tracking();
        tracking.setTime(new Date());
        tracking.setLat(lat);
        tracking.setLng(lng);
        tracking.setIdTravel(new Travel((int) location.get("id_travel")));

        log.debug("Tracking: Travel ID: {}, Lat: {}, Lng: {}",
                tracking.getIdTravel().getIdTravel(), tracking.getLat(), tracking.getLng());

        trackingRepository.save(tracking);

        // Webhook request.
        sendWebhookRequest((int) location.get("id_travel"), lat, lng);

        return new ResponseEntity("", HttpStatus.OK);
    }

    /**
     * Build and send a webhook request.
     *
     * @param travel ID of travel.
     * @param lat Latitude.
     * @param lng Longitude.
     */
    private void sendWebhookRequest(int travel, float lat, float lng) {

        Map request = new HashMap<>();
        request.put("USER", getUser());
        request.put("TYPE", "New location");
        request.put("TRAVEL_ID", travel);
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
