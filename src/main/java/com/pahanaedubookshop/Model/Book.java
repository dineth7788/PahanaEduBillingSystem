package com.pahanaedubookshop.Model;

public class Book {
    private int productId;
    private String productTitle;
    private String productDesc;
    private double productPrice;

    public Book() {}

    public Book(int productId, String productTitle, String productDesc, double productPrice) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductTitle() { return productTitle; }
    public void setProductTitle(String productTitle) { this.productTitle = productTitle; }

    public String getProductDesc() { return productDesc; }
    public void setProductDesc(String productDesc) { this.productDesc = productDesc; }

    public double getProductPrice() { return productPrice; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }
}