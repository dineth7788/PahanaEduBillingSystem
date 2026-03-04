package com.pahanaedubookshop.controller;

import com.pahanaedubookshop.dao.CustomerDAO;
import com.pahanaedubookshop.dao.ItemDAO;
import com.pahanaedubookshop.dao.OrderDAO;
import com.pahanaedubookshop.model.Bill;
import com.pahanaedubookshop.model.BillItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ItemDAO itemDAO = new ItemDAO();
    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("view".equals(action)) {
            // View Invoice
            int billId = Integer.parseInt(request.getParameter("id"));
            Bill bill = orderDAO.getBillById(billId);
            request.setAttribute("bill", bill);
            request.setAttribute("customer", customerDAO.getCustomerById(bill.getCustomerId()));
            request.getRequestDispatcher("/invoice.jsp").forward(request, response);
        } else {
            // Load Place Order Form
            request.setAttribute("customers", customerDAO.getAllCustomers());
            request.setAttribute("items", itemDAO.getAllItems());
            request.getRequestDispatcher("/order.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String[] itemIds = request.getParameterValues("itemIds");
        String[] quantities = request.getParameterValues("quantities");
        String[] unitPrices = request.getParameterValues("unitPrices");

        List<BillItem> billItems = new ArrayList<>();
        double grandTotal = 0.0;

        // Reconstruct the cart from the submitted hidden form inputs
        if (itemIds != null) {
            for (int i = 0; i < itemIds.length; i++) {
                BillItem item = new BillItem();
                item.setItemId(Integer.parseInt(itemIds[i]));
                item.setQuantity(Integer.parseInt(quantities[i]));
                item.setUnitPrice(Double.parseDouble(unitPrices[i]));
                item.setTotalPrice(item.getQuantity() * item.getUnitPrice());

                billItems.add(item);
                grandTotal += item.getTotalPrice();
            }
        }

        Bill bill = new Bill();
        bill.setCustomerId(customerId);
        bill.setTotalAmount(grandTotal);
        bill.setItems(billItems);

        // Save to database and get the new Bill ID
        int newBillId = orderDAO.createOrder(bill);

        // Redirect to the Invoice view
        response.sendRedirect("order?action=view&id=" + newBillId + "&success=true");
    }
}