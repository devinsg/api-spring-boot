package com.demo.airline.controllers;

import com.demo.airline.models.Ride;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RideControllerTest {

    @Test
    public void testGetRides() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Ride>> rideResponse = restTemplate.exchange(
                "http://localhost:8085/university/api/v1/ride/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ride>>() {
                }
        );

        List<Ride> rides = rideResponse.getBody();
        for(Ride ride : rides) {
            System.out.println("ride name:" + ride.getRideName());
        }
    }
}
