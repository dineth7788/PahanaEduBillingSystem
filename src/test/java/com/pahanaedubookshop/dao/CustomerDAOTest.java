package com.pahanaedubookshop.dao;

import com.pahanaedubookshop.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {
    private CustomerDAO customerDAO;

    @BeforeEach
    void setUp() {
        customerDAO = new CustomerDAO();
    }

    @Test
    @DisplayName("Customer DAO - Retrieve All Customers")
    void testGetAllCustomers() {
        // Execute the DAO method
        List<Customer> list = customerDAO.getAllCustomers();

        // Assertions to prove the test passes
        assertNotNull(list, "Customer list should not be null");
        System.out.println("Test passed: Successfully retrieved " + list.size() + " customers from the database.");
    }
}