package org.example.model;

public class Item {
    private int itemId;
    private String itemName;
    private double price;
    private int stockQuantity;

    public Item() {}

    public Item(int itemId, String itemName, double price, int stockQuantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}