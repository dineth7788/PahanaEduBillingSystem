package com.pahanaedubookshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch the current session, but don't create a new one if it doesn't exist
        HttpSession session = request.getSession(false);

        if (session != null) {
            // This destroys the session completely (secure exit)
            session.invalidate();
        }

        // Redirect back to the login page
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}