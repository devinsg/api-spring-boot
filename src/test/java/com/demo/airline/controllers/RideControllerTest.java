package com.demo.airline.controllers;

import com.demo.airline.models.Ride;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
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
    public void testGetRideById() {
        RestTemplate restTemplate = new RestTemplate();
        Ride ride = restTemplate.getForObject("http://localhost:8085/university/api/v1/ride/2", Ride.class);
        System.out.println("ride name:" + ride.getRideName());
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

    @Test
    public void testUpdateRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setRideId(4L);
        ride.setRideName("Anissa Kate");
        ride.setDuration(2400);

        restTemplate.put("http://localhost:8085/university/api/v1/ride/edit", ride);

        ride = restTemplate.getForObject("http://localhost:8085/university/api/v1/ride/4", Ride.class);

        System.out.println("ride name:" + ride.getRideName());

        Assertions.assertEquals("Anissa Kate", ride.getRideName());
        Assertions.assertEquals(2400, ride.getDuration());
    }

    @Test
    public void testBatchUpdate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://localhost:8085/university/api/v1/ride/batch", Object.class);

        ResponseEntity<List<Ride>> rideResponse = restTemplate.exchange(
                "http://localhost:8085/university/api/v1/ride/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ride>>() {
                }
        );

        List<Ride> rides = rideResponse.getBody();
        for(Ride ride : rides) {
            Assertions.assertEquals(new Date().getDate(), ride.getRideDate().getDate());
        }
    }
}
