package org.example.model;
import java.sql.Timestamp;

public class Bill {
    private int billId;
    private int accountNumber;
    private Timestamp dateIssued;
    private double totalAmount;

    public Bill() {}

    public Bill(int billId, int accountNumber, Timestamp dateIssued, double totalAmount) {
        this.billId = billId;
        this.accountNumber = accountNumber;
        this.dateIssued = dateIssued;
        this.totalAmount = totalAmount;
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public Timestamp getDateIssued() { return dateIssued; }
    public void setDateIssued(Timestamp dateIssued) { this.dateIssued = dateIssued; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}