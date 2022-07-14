package com.demo.airline.repositories;

import com.demo.airline.models.Ride;

import java.util.List;

public interface IRideRepository {
    Ride add(Ride ride);

    Ride add_jdbc(Ride ride);

    Ride add_statement(Ride ride);

    Ride edit(Ride ride);

    void editRides(List<Object[]> pairs);

    List<Ride> getRides();

    Ride getById(long Id);
}
