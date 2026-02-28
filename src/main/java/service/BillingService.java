package org.example.service;

import org.example.model.Item;

public class BillingService {

    // Calculates the subtotal for a specific item added to the cart
    public double calculateSubtotal(Item item, int quantity) {
        if (item == null || quantity <= 0) {
            return 0.0;
        }

        // Simple Shopping Cart Math: Price * Quantity
        return item.getPrice() * quantity;
    }

    // You can add discount logic here later if you want extra marks!
    // Example: 10% off if subtotal is over Rs. 5000
}