package com.demo.airline.controllers;

import com.demo.airline.models.Ride;
import com.demo.airline.services.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ride")
public class RideController {
    @Autowired
    private IRideService rideService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Ride>> list() {
        List<Ride> rides = rideService.getList();
        return ResponseEntity.ok().body(rides);
    }
}
