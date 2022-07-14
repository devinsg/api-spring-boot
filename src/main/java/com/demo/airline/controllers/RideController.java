package com.demo.airline.controllers;

import com.demo.airline.models.Ride;
import com.demo.airline.services.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ride")
public class RideController {
    @Autowired
    private IRideService rideService;

    @GetMapping(path = "/list")
    public @ResponseBody List<Ride> getList() {
        List<Ride> rides = rideService.getList();
        return rides;
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Ride getItem(@PathVariable(value = "id") long id) {
        Ride ride = rideService.getById(id);
        return ride;
    }

    @PostMapping(path = "/add")
    public @ResponseBody Ride add(@RequestBody final Ride ride){
        return rideService.add(ride);
    }

    @PutMapping(path = "/edit")
    public @ResponseBody Ride edit(@RequestBody final Ride ride){
        return rideService.edit(ride);
    }

    @GetMapping(path = "/batch")
    public @ResponseBody Ride batch(){
        rideService.batch();
        return null;
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody boolean delete(@PathVariable(value = "id") long id){
        return rideService.deleteById(id);
    }
}
