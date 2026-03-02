package com.pahanaedubookshop.Controller;

import com.pahanaedubookshop.Model.Customer;
import com.pahanaedubookshop.Service.CustomerService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
    private final CustomerService clientService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String route = req.getParameter("action");
        if (route == null) route = "display";

        switch (route) {
            case "create":
                req.getRequestDispatcher("/forms/customer-form.jsp").forward(req, resp);
                break;
            case "modify":
                int updateId = Integer.parseInt(req.getParameter("id"));
                Customer existingClient = clientService.fetchClientById(updateId);
                req.setAttribute("clientData", existingClient);
                req.getRequestDispatcher("/forms/customer-form.jsp").forward(req, resp);
                break;
            case "erase":
                int delId = Integer.parseInt(req.getParameter("id"));
                clientService.dropClient(delId);
                resp.sendRedirect(req.getContextPath() + "/customers");
                break;
            default:
                List<Customer> allClients = clientService.retrieveAllClients();
                req.setAttribute("clientList", allClients);
                req.getRequestDispatcher("/forms/customer-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientIdStr = req.getParameter("clientId");
        String accNumber = req.getParameter("clientAccNo");
        String name = req.getParameter("clientName");
        int age = Integer.parseInt(req.getParameter("clientAge"));
        String address = req.getParameter("clientAddress");
        String email = req.getParameter("clientEmail");
        String phone = req.getParameter("clientPhone");

        Customer clientObj = new Customer();
        clientObj.setClientAccNo(accNumber);
        clientObj.setClientName(name);
        clientObj.setClientAge(age);
        clientObj.setClientAddress(address);
        clientObj.setClientEmail(email);
        clientObj.setClientPhone(phone);

        if (clientIdStr == null || clientIdStr.trim().isEmpty()) {
            clientService.registerClient(clientObj);
        } else {
            clientObj.setClientId(Integer.parseInt(clientIdStr));
            clientService.modifyClient(clientObj);
        }
        resp.sendRedirect(req.getContextPath() + "/customers");
    }
}