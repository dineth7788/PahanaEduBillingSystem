package com.pahanaedubookshop.DAO;

import com.pahanaedubookshop.Model.Customer;
import com.pahanaedubookshop.Util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO tbl_client (client_acc_no, client_name, client_age, client_address, client_email, client_phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, customer.getClientAccNo());
            ps.setString(2, customer.getClientName());
            ps.setInt(3, customer.getClientAge());
            ps.setString(4, customer.getClientAddress());
            ps.setString(5, customer.getClientEmail());
            ps.setString(6, customer.getClientPhone());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> clientList = new ArrayList<>();
        String query = "SELECT * FROM tbl_client";
        try (Connection conn = DataBaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                clientList.add(new Customer(
                        rs.getInt("client_id"),
                        rs.getString("client_acc_no"),
                        rs.getString("client_name"),
                        rs.getInt("client_age"),
                        rs.getString("client_address"),
                        rs.getString("client_email"),
                        rs.getString("client_phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    @Override
    public Customer getCustomerById(int id) {
        String query = "SELECT * FROM tbl_client WHERE client_id = ?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("client_id"),
                        rs.getString("client_acc_no"),
                        rs.getString("client_name"),
                        rs.getInt("client_age"),
                        rs.getString("client_address"),
                        rs.getString("client_email"),
                        rs.getString("client_phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getCustomerByAccountNumber(String accountNumber) {
        String query = "SELECT * FROM tbl_client WHERE client_acc_no = ?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("client_id"),
                        rs.getString("client_acc_no"),
                        rs.getString("client_name"),
                        rs.getInt("client_age"),
                        rs.getString("client_address"),
                        rs.getString("client_email"),
                        rs.getString("client_phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE tbl_client SET client_acc_no=?, client_name=?, client_age=?, client_address=?, client_email=?, client_phone=? WHERE client_id=?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, customer.getClientAccNo());
            ps.setString(2, customer.getClientName());
            ps.setInt(3, customer.getClientAge());
            ps.setString(4, customer.getClientAddress());
            ps.setString(5, customer.getClientEmail());
            ps.setString(6, customer.getClientPhone());
            ps.setInt(7, customer.getClientId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(int id) {
        String query = "DELETE FROM tbl_client WHERE client_id=?";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}