package com.pahanaedubookshop.DAO;

import com.pahanaedubookshop.Model.Book;
import java.util.List;

public interface BookDAO {
    boolean addBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(int id);
    boolean updateBook(Book book);
    boolean deleteBook(int id);
}