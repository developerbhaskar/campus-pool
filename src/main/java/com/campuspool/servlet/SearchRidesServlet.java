package com.campuspool.servlet;

import com.campuspool.dao.RideDAO;
import com.campuspool.model.Ride;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/search-rides")
public class SearchRidesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String destination = req.getParameter("destination");
        if (destination == null) destination = "";

        String dateParam = req.getParameter("date");
        Date date = (dateParam == null || dateParam.isBlank()) ? null : Date.valueOf(dateParam);

        try {
            List<Ride> rides = new RideDAO().search(destination, date);
            req.setAttribute("rides", rides);
            req.getRequestDispatcher("/search-rides.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
