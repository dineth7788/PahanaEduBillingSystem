package org.example.dao;

import org.example.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDAO {

    // Method to add a new item to the inventory
    public boolean addItem(Item item) {
        boolean isSuccess = false;
        Connection conn = DatabaseConnection.getInstance().getConnection();

        String sql = "INSERT INTO Item (item_name, price, stock_quantity) VALUES (?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getItemName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setInt(3, item.getStockQuantity());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
                System.out.println("Item added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
        return isSuccess;
    }
}