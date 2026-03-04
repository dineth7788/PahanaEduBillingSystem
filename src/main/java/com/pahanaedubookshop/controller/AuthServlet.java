package com.pahanaedubookshop.controller;

import com.pahanaedubookshop.dao.UserDAO;
import com.pahanaedubookshop.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

// This maps the exact URL from your HTML form to this Java file!
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Get what the user typed in the boxes
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        // 2. Check the database
        User authenticatedUser = userDAO.authenticateUser(user, pass);

        // 3. Decide where to send them
        if (authenticatedUser != null) {
            // Success! Save user to session and send to Admin Dashboard
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", authenticatedUser);
            response.sendRedirect(request.getContextPath() + "/admin-dashboard.jsp");
        } else {
            // Failed! Send error message back to the login page
            request.setAttribute("errorMessage", "Invalid Username or Password!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}