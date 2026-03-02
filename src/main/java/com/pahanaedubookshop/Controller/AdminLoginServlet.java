package com.pahanaedubookshop.Controller;

import com.pahanaedubookshop.DAO.AdminDAO;
import com.pahanaedubookshop.Model.Admin;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    private final AdminDAO sysAdminDao = new AdminDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userField = req.getParameter("username");
        String passField = req.getParameter("password");

        try {
            Admin authenticatedAdmin = sysAdminDao.login(userField, passField);

            if (authenticatedAdmin != null) {
                HttpSession userSession = req.getSession(true);
                userSession.setAttribute("loggedAdmin", authenticatedAdmin); // Custom session token
                resp.sendRedirect(req.getContextPath() + "/forms/admin.jsp");
            } else {
                resp.setContentType("text/html");
                resp.getWriter().println("<h3>Authentication Denied. Invalid credentials.</h3>");
            }
        } catch (Exception ex) {
            req.setAttribute("authError", "System error occurred during login.");
            req.getRequestDispatcher("/forms/login.jsp").forward(req, resp);
        }
    }
}