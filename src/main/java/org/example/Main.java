package org.example;

import org.example.dao.DatabaseConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing Database Connection for Pahana Edu...");

        // Calling the Singleton instance to get the connection
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Connection conn = dbConnection.getConnection();

        if (conn != null) {
            System.out.println("SUCCESS: Securely connected to the pahana_edu database!");
        } else {
            System.out.println("FAILED: Could not connect. Check your MySQL credentials and status.");
        }
    }
}