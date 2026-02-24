package org.example.dao;

import org.example.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    // Method to add a new customer to the database
    public boolean addCustomer(Customer customer) {
        boolean isSuccess = false;
        Connection conn = DatabaseConnection.getInstance().getConnection();

        // SQL query to insert data. The '?' prevents SQL injection attacks!
        String sql = "INSERT INTO Customer (name, address, telephone_number, units_consumed) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getTelephoneNumber());
            pstmt.setInt(4, customer.getUnitsConsumed());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
                System.out.println("Customer added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
        return isSuccess;
    }
}