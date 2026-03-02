package com.pahanaedubookshop.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {
    // Pointing to our new unique database name
    private static final String DB_URL = "jdbc:mysql://localhost:3306/smart_edu_bookshop?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "admin123"; // Make sure this matches your MySQL root password

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}