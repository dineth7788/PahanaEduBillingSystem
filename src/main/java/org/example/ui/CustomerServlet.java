package org.example.ui;

import org.example.dao.CustomerDAO;
import org.example.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");


        // BACKEND VALIDATION 1: Check if telephone contains ONLY numbers using Regex
        if (!telephone.matches("[0-9]+")) {
            request.setAttribute("error", "Failed: Telephone number must contain only numbers!");
            request.getRequestDispatcher("customer.jsp").forward(request, response);
            return; // Stops the code here!
        }

        try {
            // BACKEND VALIDATION 2: Try to convert units to a number


            Customer newCustomer = new Customer();
            newCustomer.setName(name);
            newCustomer.setAddress(address);
            newCustomer.setTelephoneNumber(telephone);

            CustomerDAO customerDAO = new CustomerDAO();
            boolean isSaved = customerDAO.addCustomer(newCustomer);

            if (isSaved) {
                request.setAttribute("message", "Success! Customer " + name + " added to database.");
            } else {
                request.setAttribute("error", "Error: Could not save customer to database.");
            }
        } catch (NumberFormatException e) {
            // If they typed letters into the "units" box, it triggers this error instead of crashing!
            request.setAttribute("error", "Failed: Units consumed must be a valid number!");
        }

        request.getRequestDispatcher("customer.jsp").forward(request, response);
    }
}