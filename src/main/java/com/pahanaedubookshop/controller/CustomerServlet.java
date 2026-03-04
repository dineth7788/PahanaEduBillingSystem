package com.pahanaedubookshop.controller;

import com.pahanaedubookshop.dao.CustomerDAO;
import com.pahanaedubookshop.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private final CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Secure Access Check
        if (request.getSession().getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "list";

        if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            customerDAO.deleteCustomer(id);
            response.sendRedirect(request.getContextPath() + "/customers");
            return;
        }
        else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Customer existingCustomer = customerDAO.getCustomerById(id);
            request.setAttribute("customerToEdit", existingCustomer);
        }

        // Always load the list of customers for the table
        request.setAttribute("customerList", customerDAO.getAllCustomers());
        request.getRequestDispatcher("/customers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("customerId");

        Customer c = new Customer();
        c.setAccountNumber(request.getParameter("accountNumber"));
        c.setName(request.getParameter("name"));
        c.setPhone(request.getParameter("phone"));
        c.setUnitsConsumed(Integer.parseInt(request.getParameter("unitsConsumed")));
        c.setAddress(request.getParameter("address"));

        if (idStr == null || idStr.isEmpty()) {
            customerDAO.addCustomer(c); // Add New
        } else {
            c.setCustomerId(Integer.parseInt(idStr));
            customerDAO.updateCustomer(c); // Update Existing
        }

        response.sendRedirect(request.getContextPath() + "/customers");
    }
}