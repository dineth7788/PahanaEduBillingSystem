package com.pahanaedubookshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    // Points to the brand new database we just created
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pahana_edu_db?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";

    // IMPORTANT: Change this to your actual MySQL password!
    private static final String DB_PASSWORD = "admin123";

    // Private constructor to prevent anyone from creating objects of this utility class
    private DatabaseUtil() {}

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load the modern MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connection successful!");

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found. Check your pom.xml.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed! Check your username/password and make sure MySQL is running.");
            e.printStackTrace();
        }
        return connection;
    }
}