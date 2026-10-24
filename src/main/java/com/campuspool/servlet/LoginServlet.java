package com.campuspool.servlet;

import com.campuspool.dao.UserDAO;
import com.campuspool.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User user = new UserDAO().login(email, password);
            if (user == null) {
                req.setAttribute("error", "Invalid email or password.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
