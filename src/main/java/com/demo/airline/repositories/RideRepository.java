package com.demo.airline.repositories;

import com.demo.airline.models.Ride;
import com.demo.airline.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("rideRepository")
public class RideRepository implements IRideRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ride add_2(Ride ride) {
        String sqlInsert = "INSERT INTO ride_tracker(ride_name, duration) VALUES (?, ?)";
        int newId = jdbcTemplate.update(sqlInsert, ride.getRideName(), ride.getDuration());
        ride.setRideId((long) newId);
        return ride;
    }

    @Override
    public Ride add_3(Ride ride) {
        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(jdbcTemplate);

        simpleInsert.setGeneratedKeyName("ride_id");

        Map<String, Object> data = new HashMap<>();
        data.put("ride_name", ride.getRideName());
        data.put("duration", ride.getDuration());

        List<String> columns = new ArrayList<>();
        columns.add("ride_name");
        columns.add("duration");

        simpleInsert.setTableName("ride_tracker"); // table name in DB
        simpleInsert.setColumnNames(columns);

        Number newKey = simpleInsert.executeAndReturnKey(data);
        System.out.println("newKey:" + newKey);

        ride.setRideId(newKey.longValue());
        return ride;
    }

    @Override
    public Ride add(Ride ride) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = "INSERT INTO ride_tracker(ride_name, duration) VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(sql, new String[]{ "ride_id" });
                ps.setString(1, ride.getRideName());
                ps.setDouble(2, ride.getDuration());
                return ps;
            }
        }, keyHolder);

        long newId = keyHolder.getKey().longValue();
        return getById(newId);
    }

    @Override
    public List<Ride> getRides() {
        String sqlQuery = "SELECT * FROM ride_tracker ORDER BY ride_id DESC";
        List<Ride> rides = jdbcTemplate.query(sqlQuery, new RideRowMapper());
        return rides;
    }

    @Override
    public Ride getById(long id) {
        System.out.println("getById:" + id);
        String sql = "SELECT * FROM ride_tracker WHERE ride_id = ?";
        Ride ride = jdbcTemplate.queryForObject(sql, new RideRowMapper(), id);
        System.out.println("ride:" + ride.getRideName());
        return ride;
    }
}
