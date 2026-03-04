package com.pahanaedubookshop.model;

public class Customer {
    private int customerId;
    private String accountNumber;
    private String name;
    private String phone;
    private int unitsConsumed;
    private String address;

    public Customer() {}

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getUnitsConsumed() { return unitsConsumed; }
    public void setUnitsConsumed(int unitsConsumed) { this.unitsConsumed = unitsConsumed; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}