package com.pahanaedubookshop.Service;

import com.pahanaedubookshop.DAO.BookDAO;
import com.pahanaedubookshop.DAO.BookDAOImpl;
import com.pahanaedubookshop.Model.Book;

import java.util.List;

public class BookService {
    // Unique variable and method names
    private final BookDAO inventoryDao;

    public BookService() {
        this.inventoryDao = new BookDAOImpl();
    }

    public boolean registerNewBook(Book newBook) {
        return inventoryDao.addBook(newBook);
    }

    public List<Book> fetchAllInventory() {
        return inventoryDao.getAllBooks();
    }

    public Book findBookByRef(int bookId) {
        return inventoryDao.getBookById(bookId);
    }

    public boolean modifyBookDetails(Book existingBook) {
        return inventoryDao.updateBook(existingBook);
    }

    public boolean removeBookRecord(int bookId) {
        return inventoryDao.deleteBook(bookId);
    }
}