package com.pahanaedubookshop.dao;

import com.pahanaedubookshop.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAO {

    public int getTotalCustomers() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS total FROM customers";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalOrders() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS total FROM bills";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public double getTotalRevenue() {
        double total = 0.0;
        String sql = "SELECT SUM(total_amount) AS revenue FROM bills";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble("revenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }


    // NEW FUNCTION: Fetch items that are running low on stock (Quantity <= 5)
    public java.util.List<com.pahanaedubookshop.model.Item> getLowStockItems() {
        java.util.List<com.pahanaedubookshop.model.Item> lowStockList = new java.util.ArrayList<>();
        String sql = "SELECT * FROM items WHERE quantity <= 5 ORDER BY quantity ASC LIMIT 5";
        try (java.sql.Connection conn = DatabaseUtil.getConnection();
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                com.pahanaedubookshop.model.Item item = new com.pahanaedubookshop.model.Item();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setQuantity(rs.getInt("quantity"));
                lowStockList.add(item);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return lowStockList;
    }
}