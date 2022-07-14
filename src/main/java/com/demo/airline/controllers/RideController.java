package com.demo.airline.controllers;

import com.demo.airline.models.Ride;
import com.demo.airline.services.IRideService;
import com.demo.airline.util.HandleError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = "/test")
    public @ResponseBody Object test(){
        throw new DataAccessException("Testing exception thrown") {
        };
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HandleError> handle(RuntimeException exception) {
        HandleError error = new HandleError(HttpStatus.OK.value(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
