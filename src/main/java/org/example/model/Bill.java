package org.example.model;

import java.sql.Timestamp;

public class Bill {
    private int billId;
    private int accountNumber; // Links to the Customer
    private Timestamp billDate;
    private double totalAmount;

    // Getters and Setters
    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public Timestamp getBillDate() { return billDate; }
    public void setBillDate(Timestamp billDate) { this.billDate = billDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}