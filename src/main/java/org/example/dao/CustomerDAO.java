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
        String sql = "INSERT INTO customer (name, address, telephone_number) VALUES (?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getTelephoneNumber());


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

    // Method to find a customer by their Telephone Number
    public Customer getCustomerByTelephone(String telephone) {
        Customer customer = null;
        Connection conn = DatabaseConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Customer WHERE telephone_number = ?";

        try {
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, telephone);

            java.sql.ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setAccountNumber(rs.getInt("account_number"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setTelephoneNumber(rs.getString("telephone_number"));

            }
        } catch (java.sql.SQLException e) {
            System.out.println("Error fetching customer: " + e.getMessage());
        }
        return customer;
    }
}