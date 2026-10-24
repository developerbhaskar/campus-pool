package com.campuspool.servlet;

import com.campuspool.dao.BookingDAO;
import com.campuspool.dao.RideDAO;
import com.campuspool.model.Ride;
import com.campuspool.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            List<Ride> posted = new RideDAO().findByDriver(user.getId());
            List<Map<String, Object>> bookings = new BookingDAO().findPassengerBookings(user.getId());

            req.setAttribute("postedRides", posted);
            req.setAttribute("bookings", bookings);
            req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
