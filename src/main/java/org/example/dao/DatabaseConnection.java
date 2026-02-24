package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 1. Create a private static instance of the class itself
    private static DatabaseConnection instance;
    private Connection connection;

    // Database credentials (adjust these if your MySQL setup is different)
    private final String URL = "jdbc:mysql://localhost:3306/pahana_edu";
    private final String USER = "root";
    private final String PASSWORD = "admin123";

    // 2. Make the constructor private so no other class can create a new connection
    private DatabaseConnection() {
        try {
            // Load the MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    // 3. Provide a public static method to get the single instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method to return the active connection for our queries
    public Connection getConnection() {
        return connection;
    }
}
