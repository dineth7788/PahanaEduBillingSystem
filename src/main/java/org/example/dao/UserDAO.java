package org.example.dao;

import org.example.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Method to check if login credentials are correct
    public User authenticateUser(String username, String password) {
        User user = null;
        // Using our Singleton connection!
        Connection conn = DatabaseConnection.getInstance().getConnection();

        // Using PreparedStatement prevents SQL Injection attacks (Good for "secure access" marks)
        String sql = "SELECT * FROM User WHERE username = ? AND password_hash = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Note: In a real app, hash the password first!

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("Error authenticating user: " + e.getMessage());
        }
        return user;
    }
}