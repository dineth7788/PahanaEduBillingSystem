package org.example.ui;

import org.example.dao.UserDAO;
import org.example.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// This annotation tells Tomcat to send the index.jsp form data to this exact class
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. Grab the data the user typed into the HTML form
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        // 2. Ask the DAO to check the MySQL database
        UserDAO userDAO = new UserDAO();
        User loggedInUser = userDAO.authenticateUser(user, pass);

        // 3. Make a decision based on the result
        if (loggedInUser != null) {
            // SUCCESS: Create a "Session" to remember the user is logged in
            HttpSession session = request.getSession();
            session.setAttribute("activeUser", loggedInUser);

            // Send them to the main system page
            response.sendRedirect("dashboard.jsp");
        } else {
            // FAILED: Send them back to the login page with an error message
            request.setAttribute("errorMessage", "Invalid Username or Password. Please try again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}