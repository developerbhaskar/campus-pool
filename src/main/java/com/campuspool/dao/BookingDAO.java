package com.campuspool.dao;

import com.campuspool.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class BookingDAO {

    public boolean book(int rideId, int passengerId, int seats) throws SQLException {
        String check = "SELECT seats_available FROM rides WHERE id=? FOR UPDATE";
        String insert = """
                INSERT INTO bookings(ride_id, passenger_id, seats_booked)
                VALUES(?,?,?)
                """;
        String update = "UPDATE rides SET seats_available=seats_available-? WHERE id=?";

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try {
                int available;
                try (PreparedStatement ps = con.prepareStatement(check)) {
                    ps.setInt(1, rideId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) throw new SQLException("Ride not found.");
                        available = rs.getInt(1);
                    }
                }

                if (available < seats) throw new SQLException("Not enough seats available.");

                try (PreparedStatement ps = con.prepareStatement(insert)) {
                    ps.setInt(1, rideId);
                    ps.setInt(2, passengerId);
                    ps.setInt(3, seats);
                    ps.executeUpdate();
                }

                try (PreparedStatement ps = con.prepareStatement(update)) {
                    ps.setInt(1, seats);
                    ps.setInt(2, rideId);
                    ps.executeUpdate();
                }

                con.commit();
                return true;
            } catch (Exception e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    public List<Map<String, Object>> findPassengerBookings(int passengerId) throws SQLException {
        String sql = """
                SELECT b.id, b.seats_booked, b.status,
                       r.origin, r.destination, r.ride_date, r.ride_time,
                       u.full_name AS driver_name
                FROM bookings b
                JOIN rides r ON r.id=b.ride_id
                JOIN users u ON u.id=r.driver_id
                WHERE b.passenger_id=?
                ORDER BY r.ride_date DESC, r.ride_time DESC
                """;

        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, passengerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("id", rs.getInt("id"));
                    row.put("seats", rs.getInt("seats_booked"));
                    row.put("status", rs.getString("status"));
                    row.put("origin", rs.getString("origin"));
                    row.put("destination", rs.getString("destination"));
                    row.put("date", rs.getDate("ride_date"));
                    row.put("time", rs.getTime("ride_time"));
                    row.put("driver", rs.getString("driver_name"));
                    list.add(row);
                }
            }
        }
        return list;
    }

    public boolean cancel(int bookingId, int passengerId) throws SQLException {
        String find = """
                SELECT ride_id, seats_booked FROM bookings
                WHERE id=? AND passenger_id=? AND status='CONFIRMED'
                """;
        String updateBooking = "UPDATE bookings SET status='CANCELLED' WHERE id=?";
        String updateRide = "UPDATE rides SET seats_available=seats_available+? WHERE id=?";

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try {
                int rideId;
                int seats;
                try (PreparedStatement ps = con.prepareStatement(find)) {
                    ps.setInt(1, bookingId);
                    ps.setInt(2, passengerId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) return false;
                        rideId = rs.getInt("ride_id");
                        seats = rs.getInt("seats_booked");
                    }
                }

                try (PreparedStatement ps = con.prepareStatement(updateBooking)) {
                    ps.setInt(1, bookingId);
                    ps.executeUpdate();
                }

                try (PreparedStatement ps = con.prepareStatement(updateRide)) {
                    ps.setInt(1, seats);
                    ps.setInt(2, rideId);
                    ps.executeUpdate();
                }

                con.commit();
                return true;
            } catch (Exception e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}
