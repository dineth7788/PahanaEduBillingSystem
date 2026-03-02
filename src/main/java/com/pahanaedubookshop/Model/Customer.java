package com.pahanaedubookshop.Model;

public class Customer {
    private int clientId;
    private String clientAccNo;
    private String clientName;
    private int clientAge;
    private String clientAddress;
    private String clientEmail;
    private String clientPhone;

    public Customer() {}

    public Customer(int clientId, String clientAccNo, String clientName, int clientAge, String clientAddress, String clientEmail, String clientPhone) {
        this.clientId = clientId;
        this.clientAccNo = clientAccNo;
        this.clientName = clientName;
        this.clientAge = clientAge;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
    }

    public Customer(String clientAccNo, String clientName, int clientAge, String clientAddress, String clientEmail, String clientPhone) {
        this.clientAccNo = clientAccNo;
        this.clientName = clientName;
        this.clientAge = clientAge;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
    }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    public String getClientAccNo() { return clientAccNo; }
    public void setClientAccNo(String clientAccNo) { this.clientAccNo = clientAccNo; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public int getClientAge() { return clientAge; }
    public void setClientAge(int clientAge) { this.clientAge = clientAge; }

    public String getClientAddress() { return clientAddress; }
    public void setClientAddress(String clientAddress) { this.clientAddress = clientAddress; }

    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

    public String getClientPhone() { return clientPhone; }
    public void setClientPhone(String clientPhone) { this.clientPhone = clientPhone; }
}