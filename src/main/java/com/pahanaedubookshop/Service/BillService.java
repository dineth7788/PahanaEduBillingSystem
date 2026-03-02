package com.pahanaedubookshop.Service;

import com.pahanaedubookshop.DAO.BillDAO;
import com.pahanaedubookshop.DAO.BillItemDAO;
import com.pahanaedubookshop.Factory.FactoryDAO;
import com.pahanaedubookshop.Model.Bill;
import com.pahanaedubookshop.Model.BillItem;

public class BillService {

    private final BillDAO invoiceDao = FactoryDAO.getBillDAO();
    private final BillItemDAO invoiceItemDao = FactoryDAO.getBillItemDAO();

    // Custom logic flow
    public int generateNewInvoice(Bill invoiceObj) {
        // Save the main invoice first
        int generatedInvoiceId = invoiceDao.addBill(invoiceObj);

        // If successful, loop through and save all the line items
        if (generatedInvoiceId > 0 && invoiceObj.getInvoiceItems() != null) {
            for (BillItem lineItem : invoiceObj.getInvoiceItems()) {
                lineItem.setInvoiceRef(generatedInvoiceId);
                invoiceItemDao.addBillItem(lineItem);
            }
        }

        return generatedInvoiceId;
    }

    public Bill fetchInvoiceDetails(int invoiceId) {
        return invoiceDao.getBillById(invoiceId);
    }
}