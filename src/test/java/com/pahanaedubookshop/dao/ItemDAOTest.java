package com.pahanaedubookshop.dao;

import com.pahanaedubookshop.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {
    private ItemDAO itemDAO;

    @BeforeEach
    void setUp() {
        itemDAO = new ItemDAO();
    }

    @Test
    @DisplayName("Item DAO - Retrieve All Items")
    void testGetAllItems() {
        // Execute the DAO method
        List<Item> items = itemDAO.getAllItems();

        // Assertions to prove the test passes
        assertNotNull(items, "Item list should not be null");
        System.out.println("Test passed: Successfully retrieved " + items.size() + " items from the database.");
    }
}