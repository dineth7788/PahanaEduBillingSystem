package com.pahanaedubookshop.util; // Ensure this matches the folder it is in!

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseUtilTest {

    @Test
    @DisplayName("Database Connection - Successful Initialization")
    void testConnectionSuccess() {
        try {
            Connection conn = DatabaseUtil.getConnection(); // Assuming your method is static
            assertNotNull(conn, "Database connection should not be null");
            assertFalse(conn.isClosed(), "Database connection should be open");
        } catch (Exception e) {
            fail("Exception thrown during connection: " + e.getMessage());
        }
    }
}