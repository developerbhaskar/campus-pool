package com.campuspool.servlet;

import com.campuspool.dao.BookingDAO;
import com.campuspool.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/cancel-booking")
public class CancelBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            int bookingId = Integer.parseInt(req.getParameter("bookingId"));
            new BookingDAO().cancel(bookingId, user.getId());
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
