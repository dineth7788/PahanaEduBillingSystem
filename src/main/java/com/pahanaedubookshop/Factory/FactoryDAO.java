package com.pahanaedubookshop.Factory;

import com.pahanaedubookshop.DAO.*;

public class FactoryDAO {

    public static BillDAO getBillDAO() {
        return new com.pahanaedubookshop.DAO.BillDAOImpl();
    }

    public static BillItemDAO getBillItemDAO() {
        return new com.pahanaedubookshop.DAO.BillItemDAOImpl();
    }
}