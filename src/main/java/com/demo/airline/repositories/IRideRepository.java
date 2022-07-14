package com.demo.airline.repositories;

import com.demo.airline.models.Ride;

import java.util.List;

public interface IRideRepository {
    Ride add(Ride ride);

    Ride add_2(Ride ride);

    Ride add_3(Ride ride);

    List<Ride> getRides();

    Ride getById(long Id);
}
