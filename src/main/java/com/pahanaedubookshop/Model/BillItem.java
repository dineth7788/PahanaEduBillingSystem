package com.pahanaedubookshop.Model;

public class BillItem {
    private int itemRecordId;
    private int invoiceRef;
    private int productRef;
    private int buyQty;
    private double unitPrice;

    public int getItemRecordId() { return itemRecordId; }
    public void setItemRecordId(int itemRecordId) { this.itemRecordId = itemRecordId; }

    public int getInvoiceRef() { return invoiceRef; }
    public void setInvoiceRef(int invoiceRef) { this.invoiceRef = invoiceRef; }

    public int getProductRef() { return productRef; }
    public void setProductRef(int productRef) { this.productRef = productRef; }

    public int getBuyQty() { return buyQty; }
    public void setBuyQty(int buyQty) { this.buyQty = buyQty; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}