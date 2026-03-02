package com.pahanaedubookshop.Controller;

import com.pahanaedubookshop.Model.*;
import com.pahanaedubookshop.Service.BillService;
import com.pahanaedubookshop.Service.BookService;
import com.pahanaedubookshop.Service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/bill")
public class BillServlet extends HttpServlet {
    private final BillService invoiceEngine = new BillService();
    private final CustomerService clientEngine = new CustomerService();
    private final BookService inventoryEngine = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("action");
        if (cmd == null) cmd = "initForm";

        if ("findClient".equals(cmd)) {
            processClientLookup(req, resp);
        } else if ("display".equals(cmd)) {
            String paramId = req.getParameter("id");
            if (paramId == null || paramId.isEmpty()) {
                resp.sendRedirect("bill?action=fail");
                return;
            }
            try {
                int billRef = Integer.parseInt(paramId);
                Bill generatedInvoice = invoiceEngine.fetchInvoiceDetails(billRef);
                if (generatedInvoice == null) {
                    resp.sendRedirect("bill?action=fail");
                    return;
                }
                Customer associatedClient = clientEngine.fetchClientById(generatedInvoice.getClientRef());
                req.setAttribute("invoiceData", generatedInvoice);
                req.setAttribute("clientData", associatedClient);
                req.getRequestDispatcher("/forms/bill-view.jsp").forward(req, resp);
            } catch (NumberFormatException ex) {
                resp.sendRedirect("bill?action=fail");
            }
        } else if ("fail".equals(cmd)) {
            req.setAttribute("issueMsg", "Could not process your invoice request.");
            req.getRequestDispatcher("/forms/error.jsp").forward(req, resp);
        } else {
            List<Customer> availableClients = clientEngine.retrieveAllClients();
            List<Book> availableProducts = inventoryEngine.fetchAllInventory();

            req.setAttribute("clients", availableClients);
            req.setAttribute("products", availableProducts);
            req.getRequestDispatcher("/forms/bill-form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String clientRefParam = req.getParameter("targetClientId");
            if (clientRefParam == null || clientRefParam.isEmpty()) {
                req.setAttribute("issueMsg", "No client selected.");
                doGet(req, resp);
                return;
            }

            int validClientId = Integer.parseInt(clientRefParam.trim());
            Customer verifiedClient = clientEngine.fetchClientById(validClientId);

            if (verifiedClient == null) {
                req.setAttribute("issueMsg", "System could not locate the client.");
                doGet(req, resp);
                return;
            }

            Map<Integer, Integer> cartData = new HashMap<>();
            List<Book> entireInventory = inventoryEngine.fetchAllInventory();

            for (Book prod : entireInventory) {
                String amtParam = req.getParameter("orderQty_" + prod.getProductId());
                if (amtParam != null && !amtParam.trim().isEmpty()) {
                    try {
                        int amount = Integer.parseInt(amtParam.trim());
                        if (amount > 0) cartData.put(prod.getProductId(), amount);
                    } catch (NumberFormatException ignored) {}
                }
            }

            if (cartData.isEmpty()) {
                req.setAttribute("issueMsg", "Cart is empty. Add at least one item.");
                doGet(req, resp);
                return;
            }

            double aggregateTotal = 0;
            List<BillItem> lineItems = new ArrayList<>();

            for (Map.Entry<Integer, Integer> entry : cartData.entrySet()) {
                Book mappedProd = inventoryEngine.findBookByRef(entry.getKey());
                if (mappedProd == null) continue;

                double cost = mappedProd.getProductPrice();
                int qty = entry.getValue();

                BillItem itemRow = new BillItem();
                itemRow.setProductRef(mappedProd.getProductId());
                itemRow.setBuyQty(qty);
                itemRow.setUnitPrice(cost);
                lineItems.add(itemRow);

                aggregateTotal += (cost * qty);
            }

            if (aggregateTotal <= 0) {
                req.setAttribute("issueMsg", "Total calculation error.");
                doGet(req, resp);
                return;
            }

            Bill finalInvoice = new Bill();
            finalInvoice.setClientRef(validClientId);
            finalInvoice.setInvoiceDate(new Date());
            finalInvoice.setGrandTotal(aggregateTotal);
            finalInvoice.setInvoiceItems(lineItems);

            int savedInvoiceId = invoiceEngine.generateNewInvoice(finalInvoice);

            if (savedInvoiceId > 0) {
                resp.sendRedirect("bill?action=display&id=" + savedInvoiceId + "&print=true");
            } else {
                req.setAttribute("issueMsg", "Database insertion failed.");
                doGet(req, resp);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("issueMsg", "System error: " + ex.getMessage());
            doGet(req, resp);
        }
    }

    private void processClientLookup(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String accNum = req.getParameter("memberCode");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (accNum == null || accNum.trim().isEmpty()) {
            resp.getWriter().write("{\"status\": false, \"info\": \"Member code missing\"}");
            return;
        }

        try {
            Customer found = clientEngine.fetchClientByAccNo(accNum.trim());
            if (found != null) {
                resp.getWriter().write("{"
                        + "\"status\": true,"
                        + "\"clientDetails\": {"
                        + "\"id\": " + found.getClientId() + ","
                        + "\"accNo\": \"" + parseJson(found.getClientAccNo()) + "\","
                        + "\"name\": \"" + parseJson(found.getClientName()) + "\","
                        + "\"email\": \"" + parseJson(found.getClientEmail()) + "\","
                        + "\"phone\": \"" + parseJson(found.getClientPhone()) + "\""
                        + "}"
                        + "}");
            } else {
                resp.getWriter().write("{\"status\": false, \"info\": \"No matching records\"}");
            }
        } catch (Exception e) {
            resp.getWriter().write("{\"status\": false, \"info\": \"Lookup failure\"}");
        }
    }

    private String parseJson(String inputStr) {
        if (inputStr == null) return "";
        return inputStr.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}