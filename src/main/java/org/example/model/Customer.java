package org.example.model;

public class Customer {
    // Private attributes for Encapsulation (OOP)
    private int accountNumber;
    private String name;
    private String address;
    private String telephoneNumber;
    private int unitsConsumed;

    // Default Constructor
    public Customer() {}

    // Parameterized Constructor
    public Customer(int accountNumber, String name, String address, String telephoneNumber, int unitsConsumed) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.unitsConsumed = unitsConsumed;
    }

    // Public Getters and Setters
    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTelephoneNumber() { return telephoneNumber; }
    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }

}