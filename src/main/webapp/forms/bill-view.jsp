<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedubookshop.Model.*" %>
<%@ page import="com.pahanaedubookshop.Service.BookService" %>
<%@ page import="java.util.List" %>
<%
    Bill inv = (Bill) request.getAttribute("invoiceData");
    Customer cli = (Customer) request.getAttribute("clientData");
    if (inv == null) return;

    BookService invService = new BookService();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Invoice #<%= inv.getInvoiceId() %></title>
    <style>
        body { font-family: 'Helvetica Neue', Arial, sans-serif; background: #f4f7f6; padding: 20px; color: #333; }
        .receipt { max-width: 800px; margin: 0 auto; background: white; padding: 40px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .r-header { text-align: center; border-bottom: 2px solid #2c3e50; padding-bottom: 20px; margin-bottom: 30px; }
        .r-title { font-size: 24px; font-weight: bold; color: #2c3e50; }
        .meta-data { display: flex; justify-content: space-between; margin-bottom: 30px; }
        .r-table { width: 100%; border-collapse: collapse; margin-bottom: 30px; }
        .r-table th, .r-table td { padding: 12px; text-align: left; border-bottom: 1px solid #eee; }
        .r-table th { background: #f8f9fa; }
        .r-total { font-size: 20px; font-weight: bold; text-align: right; color: #e74c3c; }
        .print-btn { display: block; width: 200px; margin: 30px auto; background: #3498db; color: white; border: none; padding: 12px; text-align: center; border-radius: 4px; cursor: pointer; font-size: 16px; text-decoration: none; }
        @media print { body { background: white; padding: 0; } .receipt { box-shadow: none; padding: 0; } .no-print { display: none !important; } }
    </style>
</head>
<body>
<div class="receipt">
    <div class="r-header">
        <div class="r-title">SMART EDU BOOKSHOP - OFFICIAL RECEIPT</div>
        <div>Knowledge is Power</div>
    </div>

    <div class="meta-data">
        <div>
            <strong>Billed To:</strong><br>
            <%= cli != null ? cli.getClientName() : "Guest" %><br>
            Mem Code: <%= cli != null ? cli.getClientAccNo() : "N/A" %><br>
            <%= cli != null ? cli.getClientPhone() : "" %>
        </div>
        <div style="text-align: right;">
            <strong>Invoice Ref:</strong> #<%= inv.getInvoiceId() %><br>
            <strong>Date Issued:</strong> <%= new java.text.SimpleDateFormat("MMM dd, yyyy HH:mm").format(inv.getInvoiceDate()) %><br>
            <strong>Status:</strong> CLEARED
        </div>
    </div>

    <table class="r-table">
        <thead>
        <tr>
            <th>Item Description</th>
            <th>Qty</th>
            <th>Unit Cost</th>
            <th>Subtotal</th>
        </tr>
        </thead>
        <tbody>
        <% for (BillItem line : inv.getInvoiceItems()) {
            Book mappedBook = invService.findBookByRef(line.getProductRef());
            double lineMath = line.getUnitPrice() * line.getBuyQty();
        %>
        <tr>
            <td><%= mappedBook != null ? mappedBook.getProductTitle() : "Product #" + line.getProductRef() %></td>
            <td><%= line.getBuyQty() %></td>
            <td>Rs. <%= String.format("%.2f", line.getUnitPrice()) %></td>
            <td>Rs. <%= String.format("%.2f", lineMath) %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <div class="r-total">
        GRAND TOTAL: Rs. <%= String.format("%.2f", inv.getGrandTotal()) %>
    </div>

    <div style="text-align:center; margin-top: 50px; color:#7f8c8d; font-size: 0.9em;">
        Thank you for shopping at Smart Edu! All sales are final.
    </div>

    <button class="print-btn no-print" onclick="window.print()">Print Receipt</button>
    <a href="${pageContext.request.contextPath}/admin.jsp" class="print-btn no-print" style="background:#7f8c8d; margin-top:10px;">Return to Workspace</a>
</div>

<script>
    window.onload = function() {
        if (new URLSearchParams(window.location.search).get('print') === 'true') {
            setTimeout(window.print, 500);
        }
    };
</script>
</body>
</html>