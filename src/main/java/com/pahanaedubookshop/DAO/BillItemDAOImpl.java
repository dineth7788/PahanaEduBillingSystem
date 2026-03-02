package com.pahanaedubookshop.DAO;

import com.pahanaedubookshop.Model.BillItem;
import com.pahanaedubookshop.Util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillItemDAOImpl implements BillItemDAO {

    @Override
    public boolean addBillItem(BillItem item) {
        String query = "INSERT INTO tbl_invoice_item (invoice_ref, product_ref, buy_qty, unit_price, line_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, item.getInvoiceRef());
            ps.setInt(2, item.getProductRef());
            ps.setInt(3, item.getBuyQty());
            ps.setDouble(4, item.getUnitPrice());
            ps.setDouble(5, item.getUnitPrice() * item.getBuyQty()); // Subtotal math

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}