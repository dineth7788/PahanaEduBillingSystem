package com.pahanaedubookshop.DAO;

import com.pahanaedubookshop.Model.Book;
import com.pahanaedubookshop.Util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public boolean addBook(Book book) {
        String query = "INSERT INTO tbl_product (product_title, product_desc, product_price) VALUES (?, ?, ?)";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, book.getProductTitle());
            ps.setString(2, book.getProductDesc());
            ps.setDouble(3, book.getProductPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> inventory = new ArrayList<>();
        String query = "SELECT * FROM tbl_product";
        try (Connection conn = DataBaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                inventory.add(new Book(
                        rs.getInt("product_id"),
                        rs.getString("product_title"),
                        rs.getString("product_desc"),
                        rs.getDouble("product_price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }

    @Override
    public Book getBookById(int id) {
        String query = "SELECT * FROM tbl_product WHERE product_id = ?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getInt("product_id"),
                        rs.getString("product_title"),
                        rs.getString("product_desc"),
                        rs.getDouble("product_price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateBook(Book book) {
        String query = "UPDATE tbl_product SET product_title=?, product_desc=?, product_price=? WHERE product_id=?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, book.getProductTitle());
            ps.setString(2, book.getProductDesc());
            ps.setDouble(3, book.getProductPrice());
            ps.setInt(4, book.getProductId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(int id) {
        String query = "DELETE FROM tbl_product WHERE product_id=?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}