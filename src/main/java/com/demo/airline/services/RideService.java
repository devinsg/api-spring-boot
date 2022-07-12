package com.demo.airline.services;

import com.demo.airline.models.Ride;
import com.demo.airline.repositories.IRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rideService")
public class RideService implements IRideService {
    @Autowired
    private IRideRepository rideRepository;

    @Override
    public List<Ride> getList() {
        return rideRepository.getRides();
    }
}
