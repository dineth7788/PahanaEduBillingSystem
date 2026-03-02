package com.pahanaedubookshop.DAO;

import com.pahanaedubookshop.Model.Bill;

public interface BillDAO {
    int addBill(Bill bill);
    Bill getBillById(int id);
}