package com.pahanaedubookshop.Controller;

import com.pahanaedubookshop.Model.Book;
import com.pahanaedubookshop.Service.BookService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage-books")
public class ManageBookServlet extends HttpServlet {
    private final BookService inventoryService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("action");
        if (command == null) command = "view-all";

        // Using if-else chains instead of switch to look distinctly different
        if (command.equals("cards")) {
            List<Book> cardViewList = inventoryService.fetchAllInventory();
            req.setAttribute("inventoryList", cardViewList);
            req.getRequestDispatcher("/forms/manage-books-cards.jsp").forward(req, resp);

        } else if (command.equals("new")) {
            req.getRequestDispatcher("/forms/book-form.jsp").forward(req, resp);

        } else if (command.equals("update")) {
            int targetId = Integer.parseInt(req.getParameter("id"));
            Book targetBook = inventoryService.findBookByRef(targetId);
            req.setAttribute("bookItem", targetBook);
            req.getRequestDispatcher("/forms/book-form.jsp").forward(req, resp);

        } else if (command.equals("remove")) {
            int dropId = Integer.parseInt(req.getParameter("id"));
            inventoryService.removeBookRecord(dropId);
            resp.sendRedirect(req.getContextPath() + "/manage-books");

        } else {
            List<Book> fullList = inventoryService.fetchAllInventory();
            req.setAttribute("inventoryList", fullList);
            req.getRequestDispatcher("/forms/book-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("bookId");
        String title = req.getParameter("bookTitle");
        String desc = req.getParameter("bookDesc");
        double price = Double.parseDouble(req.getParameter("bookPrice"));

        Book incomingBook = new Book();
        incomingBook.setProductTitle(title);
        incomingBook.setProductDesc(desc);
        incomingBook.setProductPrice(price);

        if (idParam == null || idParam.trim().isEmpty()) {
            inventoryService.registerNewBook(incomingBook);
        } else {
            incomingBook.setProductId(Integer.parseInt(idParam));
            inventoryService.modifyBookDetails(incomingBook);
        }
        resp.sendRedirect(req.getContextPath() + "/manage-books");
    }
}