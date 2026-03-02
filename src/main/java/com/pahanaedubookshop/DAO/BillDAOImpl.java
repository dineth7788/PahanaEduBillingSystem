package com.pahanaedubookshop.DAO;

import com.pahanaedubookshop.Model.Bill;
import com.pahanaedubookshop.Model.BillItem;
import com.pahanaedubookshop.Util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    @Override
    public int addBill(Bill bill) {
        String query = "INSERT INTO tbl_invoice (client_ref, invoice_date, grand_total) VALUES (?, ?, ?)";
        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, bill.getClientRef());
            ps.setTimestamp(2, new Timestamp(bill.getInvoiceDate().getTime()));
            ps.setDouble(3, bill.getGrandTotal());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) return -1;

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Bill getBillById(int id) {
        Bill bill = null;
        String invoiceQuery = "SELECT * FROM tbl_invoice WHERE invoice_id = ?";
        String itemsQuery = "SELECT * FROM tbl_invoice_item WHERE invoice_ref = ?";

        try (Connection conn = DataBaseUtil.getConnection();
             PreparedStatement psInvoice = conn.prepareStatement(invoiceQuery);
             PreparedStatement psItems = conn.prepareStatement(itemsQuery)) {

            psInvoice.setInt(1, id);
            ResultSet rsInvoice = psInvoice.executeQuery();

            if (rsInvoice.next()) {
                bill = new Bill();
                bill.setInvoiceId(rsInvoice.getInt("invoice_id"));
                bill.setClientRef(rsInvoice.getInt("client_ref"));
                bill.setInvoiceDate(new Date(rsInvoice.getTimestamp("invoice_date").getTime()));
                bill.setGrandTotal(rsInvoice.getDouble("grand_total"));

                // Fetching the linked items
                psItems.setInt(1, id);
                ResultSet rsItems = psItems.executeQuery();

                List<BillItem> itemList = new ArrayList<>();
                while (rsItems.next()) {
                    BillItem item = new BillItem();
                    item.setItemRecordId(rsItems.getInt("item_record_id"));
                    item.setInvoiceRef(rsItems.getInt("invoice_ref"));
                    item.setProductRef(rsItems.getInt("product_ref"));
                    item.setBuyQty(rsItems.getInt("buy_qty"));
                    item.setUnitPrice(rsItems.getDouble("unit_price"));
                    itemList.add(item);
                }
                bill.setInvoiceItems(itemList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }
}