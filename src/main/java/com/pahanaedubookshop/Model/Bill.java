package com.pahanaedubookshop.Model;

import java.util.Date;
import java.util.List;

public class Bill {
    private int invoiceId;
    private int clientRef;
    private Date invoiceDate;
    private double grandTotal;
    private List<BillItem> invoiceItems;

    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    public int getClientRef() { return clientRef; }
    public void setClientRef(int clientRef) { this.clientRef = clientRef; }

    public Date getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }

    public double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(double grandTotal) { this.grandTotal = grandTotal; }

    public List<BillItem> getInvoiceItems() { return invoiceItems; }
    public void setInvoiceItems(List<BillItem> invoiceItems) { this.invoiceItems = invoiceItems; }
}