package com.campuspool.servlet;

import com.campuspool.dao.RideDAO;
import com.campuspool.model.Ride;
import com.campuspool.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/post-ride")
public class PostRideServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        Ride ride = new Ride();
        ride.setDriverId(user.getId());
        ride.setOrigin(req.getParameter("origin"));
        ride.setDestination(req.getParameter("destination"));
        ride.setRideDate(Date.valueOf(req.getParameter("rideDate")));
        ride.setRideTime(Time.valueOf(req.getParameter("rideTime") + ":00"));
        ride.setSeatsAvailable(Integer.parseInt(req.getParameter("seats")));
        ride.setPrice(new BigDecimal(req.getParameter("price")));
        ride.setNotes(req.getParameter("notes"));

        try {
            new RideDAO().create(ride);
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
