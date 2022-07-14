package com.demo.airline.util;

import com.demo.airline.models.Ride;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RideRowMapper implements RowMapper<Ride> {

    @Override
    public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ride ride = new Ride();
        ride.setRideId(rs.getLong("ride_id"));
        ride.setRideName(rs.getString("ride_name"));
        ride.setDuration(rs.getLong("duration"));
        return ride;
    }
}
