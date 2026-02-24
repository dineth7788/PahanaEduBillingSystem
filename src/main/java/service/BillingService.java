package org.example.service;

import org.example.model.Customer;

public class BillingService {

    // A method to calculate the total bill based on units consumed
    public double calculateElectricityBill(Customer customer) {
        int units = customer.getUnitsConsumed();
        double totalBill = 0.0;

        // Requirement: Mathematical logic for tiered billing
        if (units <= 50) {
            totalBill = units * 10.0; // Rs. 10 per unit for the first 50
        } else if (units <= 100) {
            totalBill = (50 * 10.0) + ((units - 50) * 15.0); // Rs. 15 per unit for the next 50
        } else {
            totalBill = (50 * 10.0) + (50 * 15.0) + ((units - 100) * 20.0); // Rs. 20 for anything above 100
        }

        // Add a fixed monthly service charge of Rs. 500
        totalBill += 500.0;

        return totalBill;
    }
}