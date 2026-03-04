package com.pahanaedubookshop.dao;

import com.pahanaedubookshop.model.Customer;
import com.pahanaedubookshop.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void addCustomer(Customer c) {
        String sql = "INSERT INTO customers (account_number, name, phone, units_consumed, address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getAccountNumber());
            ps.setString(2, c.getName());
            ps.setString(3, c.getPhone());
            ps.setInt(4, c.getUnitsConsumed());
            ps.setString(5, c.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers ORDER BY customer_id DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getInt("customer_id"));
                c.setAccountNumber(rs.getString("account_number"));
                c.setName(rs.getString("name"));
                c.setPhone(rs.getString("phone"));
                c.setUnitsConsumed(rs.getInt("units_consumed"));
                c.setAddress(rs.getString("address"));
                list.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Customer getCustomerById(int id) {
        Customer c = null;
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Customer();
                c.setCustomerId(rs.getInt("customer_id"));
                c.setAccountNumber(rs.getString("account_number"));
                c.setName(rs.getString("name"));
                c.setPhone(rs.getString("phone"));
                c.setUnitsConsumed(rs.getInt("units_consumed"));
                c.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return c;
    }

    public void updateCustomer(Customer c) {
        String sql = "UPDATE customers SET account_number=?, name=?, phone=?, units_consumed=?, address=? WHERE customer_id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getAccountNumber());
            ps.setString(2, c.getName());
            ps.setString(3, c.getPhone());
            ps.setInt(4, c.getUnitsConsumed());
            ps.setString(5, c.getAddress());
            ps.setInt(6, c.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}