package com.campuspool.dao;

import com.campuspool.model.Ride;
import com.campuspool.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RideDAO {

    public boolean create(Ride ride) throws SQLException {
        String sql = """
                INSERT INTO rides(driver_id, origin, destination, ride_date, ride_time,
                                  seats_available, price, notes)
                VALUES(?,?,?,?,?,?,?,?)
                """;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ride.getDriverId());
            ps.setString(2, ride.getOrigin());
            ps.setString(3, ride.getDestination());
            ps.setDate(4, ride.getRideDate());
            ps.setTime(5, ride.getRideTime());
            ps.setInt(6, ride.getSeatsAvailable());
            ps.setBigDecimal(7, ride.getPrice());
            ps.setString(8, ride.getNotes());
            return ps.executeUpdate() == 1;
        }
    }

    public List<Ride> search(String destination, Date date) throws SQLException {
        String sql = """
                SELECT r.*, u.full_name
                FROM rides r
                JOIN users u ON u.id = r.driver_id
                WHERE r.seats_available > 0
                  AND r.destination LIKE ?
                  AND (? IS NULL OR r.ride_date = ?)
                ORDER BY r.ride_date, r.ride_time
                """;

        List<Ride> rides = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + destination + "%");
            ps.setDate(2, date);
            ps.setDate(3, date);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ride r = new Ride();
                    r.setId(rs.getInt("id"));
                    r.setDriverId(rs.getInt("driver_id"));
                    r.setDriverName(rs.getString("full_name"));
                    r.setOrigin(rs.getString("origin"));
                    r.setDestination(rs.getString("destination"));
                    r.setRideDate(rs.getDate("ride_date"));
                    r.setRideTime(rs.getTime("ride_time"));
                    r.setSeatsAvailable(rs.getInt("seats_available"));
                    r.setPrice(rs.getBigDecimal("price"));
                    r.setNotes(rs.getString("notes"));
                    rides.add(r);
                }
            }
        }
        return rides;
    }

    public List<Ride> findByDriver(int driverId) throws SQLException {
        String sql = """
                SELECT r.*, u.full_name
                FROM rides r JOIN users u ON u.id=r.driver_id
                WHERE r.driver_id=?
                ORDER BY r.ride_date DESC, r.ride_time DESC
                """;
        return queryRides(sql, driverId);
    }

    private List<Ride> queryRides(String sql, int id) throws SQLException {
        List<Ride> rides = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ride r = new Ride();
                    r.setId(rs.getInt("id"));
                    r.setDriverId(rs.getInt("driver_id"));
                    r.setDriverName(rs.getString("full_name"));
                    r.setOrigin(rs.getString("origin"));
                    r.setDestination(rs.getString("destination"));
                    r.setRideDate(rs.getDate("ride_date"));
                    r.setRideTime(rs.getTime("ride_time"));
                    r.setSeatsAvailable(rs.getInt("seats_available"));
                    r.setPrice(rs.getBigDecimal("price"));
                    r.setNotes(rs.getString("notes"));
                    rides.add(r);
                }
            }
        }
        return rides;
    }
}
