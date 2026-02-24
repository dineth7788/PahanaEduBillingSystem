package org.example.ui;

import org.example.dao.ItemDAO;
import org.example.model.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. Grab inputs from the item.jsp form
        String itemName = request.getParameter("itemName");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        // 2. Map data to the Item model
        Item newItem = new Item();
        newItem.setItemName(itemName);
        newItem.setPrice(price);
        newItem.setStockQuantity(stock);

        // 3. Send to DAO to save in MySQL
        ItemDAO itemDAO = new ItemDAO();
        boolean isSaved = itemDAO.addItem(newItem);

        // 4. Return success or failure message
        if (isSaved) {
            request.setAttribute("message", "Success! '" + itemName + "' added to inventory.");
        } else {
            request.setAttribute("message", "Error: Could not save the item.");
        }

        // Keep the user on the same page to see the message or add another item
        request.getRequestDispatcher("item.jsp").forward(request, response);
    }
}