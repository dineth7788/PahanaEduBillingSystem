package com.pahanaedubookshop.dao;

import com.pahanaedubookshop.model.Bill;
import com.pahanaedubookshop.model.BillItem;
import com.pahanaedubookshop.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public int createOrder(Bill bill) {
        int generatedBillId = -1;
        String billSql = "INSERT INTO bills (customer_id, total_amount) VALUES (?, ?)";
        String itemSql = "INSERT INTO bill_items (bill_id, item_id, quantity, unit_price, total_price) VALUES (?, ?, ?, ?, ?)";

        // We deduct the quantity from inventory when an order is placed
        String updateStockSql = "UPDATE items SET quantity = quantity - ? WHERE item_id = ?";

        try (Connection conn = DatabaseUtil.getConnection()) {
            // 1. Insert Master Bill
            try (PreparedStatement psBill = conn.prepareStatement(billSql, Statement.RETURN_GENERATED_KEYS)) {
                psBill.setInt(1, bill.getCustomerId());
                psBill.setDouble(2, bill.getTotalAmount());
                psBill.executeUpdate();

                ResultSet rs = psBill.getGeneratedKeys();
                if (rs.next()) {
                    generatedBillId = rs.getInt(1);
                }
            }

            // 2. Insert Bill Items & Update Stock
            if (generatedBillId != -1) {
                try (PreparedStatement psItem = conn.prepareStatement(itemSql);
                     PreparedStatement psStock = conn.prepareStatement(updateStockSql)) {

                    for (BillItem item : bill.getItems()) {
                        psItem.setInt(1, generatedBillId);
                        psItem.setInt(2, item.getItemId());
                        psItem.setInt(3, item.getQuantity());
                        psItem.setDouble(4, item.getUnitPrice());
                        psItem.setDouble(5, item.getTotalPrice());
                        psItem.executeUpdate();

                        psStock.setInt(1, item.getQuantity());
                        psStock.setInt(2, item.getItemId());
                        psStock.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }

        return generatedBillId; // Returns the ID so we can print the invoice!
    }

    // Method to fetch the bill for the Invoice page
    public Bill getBillById(int billId) {
        Bill bill = null;
        String sql = "SELECT * FROM bills WHERE bill_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setCustomerId(rs.getInt("customer_id"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setBillDate(rs.getTimestamp("bill_date"));

                // Fetch Items
                List<BillItem> items = new ArrayList<>();
                String itemSql = "SELECT bi.*, i.item_name FROM bill_items bi JOIN items i ON bi.item_id = i.item_id WHERE bi.bill_id = ?";
                try (PreparedStatement psItems = conn.prepareStatement(itemSql)) {
                    psItems.setInt(1, billId);
                    ResultSet rsItems = psItems.executeQuery();
                    while (rsItems.next()) {
                        BillItem item = new BillItem();
                        item.setItemId(rsItems.getInt("item_id"));
                        item.setItemName(rsItems.getString("item_name"));
                        item.setQuantity(rsItems.getInt("quantity"));
                        item.setUnitPrice(rsItems.getDouble("unit_price"));
                        item.setTotalPrice(rsItems.getDouble("total_price"));
                        items.add(item);
                    }
                }
                bill.setItems(items);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return bill;
    }
}