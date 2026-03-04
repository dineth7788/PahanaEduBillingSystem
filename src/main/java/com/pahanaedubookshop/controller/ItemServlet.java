package com.pahanaedubookshop.controller;

import com.pahanaedubookshop.dao.ItemDAO;
import com.pahanaedubookshop.model.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    private final ItemDAO itemDAO = new ItemDAO();

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
            itemDAO.deleteItem(id);
            response.sendRedirect(request.getContextPath() + "/items");
            return;
        }
        else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Item existingItem = itemDAO.getItemById(id);
            request.setAttribute("itemToEdit", existingItem);
        }

        request.setAttribute("itemList", itemDAO.getAllItems());
        request.getRequestDispatcher("/items.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("itemId");

        Item item = new Item();
        item.setItemName(request.getParameter("itemName"));
        item.setPrice(Double.parseDouble(request.getParameter("price")));
        item.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        if (idStr == null || idStr.isEmpty()) {
            itemDAO.addItem(item); // Add New
        } else {
            item.setItemId(Integer.parseInt(idStr));
            itemDAO.updateItem(item); // Update Existing
        }

        response.sendRedirect(request.getContextPath() + "/items");
    }
}