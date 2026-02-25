package org.example.ui;

import org.example.dao.CustomerDAO;
import org.example.model.Customer;
import org.example.service.BillingService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BillingServlet")
public class BillingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. Grab the phone number the user typed into the web page
        String telephone = request.getParameter("telephone");

        // 2. Ask the DAO to search MySQL for a customer with that exact number
        CustomerDAO customerDAO = new CustomerDAO();
        Customer foundCustomer = customerDAO.getCustomerByTelephone(telephone);

        // 3. Make a decision based on whether the customer exists
        if (foundCustomer != null) {

            // SUCCESS: We found them! Send them to our Service class to do the complex math
            BillingService billingService = new BillingService();
            double totalAmount = billingService.calculateElectricityBill(foundCustomer);

            // Attach the customer object and the final price tag to the response
            request.setAttribute("customerDetails", foundCustomer);
            request.setAttribute("billAmount", totalAmount);

        } else {
            // FAIL: Nobody in the database has that phone number
            request.setAttribute("error", "No customer found with telephone number: " + telephone);
        }

        // Send the user back to the billing page so they can see the invoice (or the error)
        request.getRequestDispatcher("billing.jsp").forward(request, response);
    }
}