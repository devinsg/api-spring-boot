package com.demo.airline.repositories;

import com.demo.airline.models.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("rideRepository")
public class RideRepository implements IRideRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Ride> getRides() {
        List<Ride> rides = new ArrayList<>();

        Ride ride1 = new Ride();
        ride1.setRideId(1L);
        ride1.setRideName("Nina Mercedes");
        rides.add(ride1);

        return rides;
    }

}
