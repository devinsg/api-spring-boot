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

    @Test
    public void testUpdateRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setRideName("Ride Test");
        ride.setDuration(500);

        restTemplate.put("http://localhost:8085/university/api/v1/ride/edit", ride);
    }

    @Test
    public void testCreateRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setRideName("Ride Test");
        ride.setDuration(500);

        ride = restTemplate.postForObject("http://localhost:8085/university/api/v1/ride/add", ride, Ride.class);

        System.out.println("Ride:" + ride);
    }
}
