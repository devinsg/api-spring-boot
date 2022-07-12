package com.demo.airline.repositories;

import com.demo.airline.models.Ride;

import java.util.List;

public interface IRideRepository {
    List<Ride> getRides();
}
