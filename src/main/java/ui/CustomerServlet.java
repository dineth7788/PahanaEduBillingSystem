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
        // 1. Grab the data the user typed into the web form
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        int units = Integer.parseInt(request.getParameter("units"));

        // 2. Put that data into our Customer Blueprint
        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setAddress(address);
        newCustomer.setTelephoneNumber(telephone);
        newCustomer.setUnitsConsumed(units);

        // 3. Ask the DAO to save it to MySQL
        CustomerDAO customerDAO = new CustomerDAO();
        boolean isSaved = customerDAO.addCustomer(newCustomer);

        // 4. Send a success message back to the web page
        if (isSaved) {
            request.setAttribute("message", "Success! Customer " + name + " added to database.");
        } else {
            request.setAttribute("message", "Error: Could not save customer.");
        }

        request.getRequestDispatcher("customer.jsp").forward(request, response);
    }
}