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
        String itemName = request.getParameter("itemName");
        String priceStr = request.getParameter("price");
        String stockStr = request.getParameter("stock");

        try {
            // BACKEND VALIDATION: Securely attempt to parse the numbers
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);

            Item newItem = new Item();
            newItem.setItemName(itemName);
            newItem.setPrice(price);
            newItem.setStockQuantity(stock);

            ItemDAO itemDAO = new ItemDAO();
            boolean isSaved = itemDAO.addItem(newItem);

            if (isSaved) {
                request.setAttribute("message", "Success! '" + itemName + "' added to inventory.");
            } else {
                request.setAttribute("error", "Error: Could not save the item.");
            }
        } catch (NumberFormatException e) {
            // Catches any letters typed into the number boxes
            request.setAttribute("error", "Failed: Price and Stock must be valid numbers!");
        }

        request.getRequestDispatcher("item.jsp").forward(request, response);
    }
}