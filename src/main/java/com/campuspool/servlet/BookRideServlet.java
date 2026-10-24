package com.campuspool.servlet;

import com.campuspool.dao.BookingDAO;
import com.campuspool.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/book-ride")
public class BookRideServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        int rideId = Integer.parseInt(req.getParameter("rideId"));
        int seats = Integer.parseInt(req.getParameter("seats"));

        try {
            new BookingDAO().book(rideId, user.getId(), seats);
            resp.sendRedirect(req.getContextPath() + "/dashboard?booked=1");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/search-rides.jsp").forward(req, resp);
        }
    }
}
