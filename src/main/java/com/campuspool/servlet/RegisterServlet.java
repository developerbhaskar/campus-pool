package com.campuspool.servlet;

import com.campuspool.dao.UserDAO;
import com.campuspool.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = new User();
        user.setFullName(req.getParameter("fullName"));
        user.setEmail(req.getParameter("email"));
        user.setPhone(req.getParameter("phone"));
        String password = req.getParameter("password");

        try {
            UserDAO dao = new UserDAO();
            if (dao.emailExists(user.getEmail())) {
                req.setAttribute("error", "Email is already registered.");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }
            dao.register(user, password);
            resp.sendRedirect(req.getContextPath() + "/login.jsp?registered=1");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
