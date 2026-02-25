<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Security check to ensure the user is logged in
    if(session.getAttribute("activeUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Pahana Edu - Billing System</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; }
        .container { background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        h2 { color: #333; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold;}
        input[type="text"] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #0056b3; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; margin-top: 10px;}
        button:hover { background-color: #004494; }
        .back-btn { background-color: #6c757d; display: block; text-align: center; text-decoration: none; margin-top: 10px; padding: 10px; color: white; border-radius: 4px; }
        .error-message { text-align: center; font-weight: bold; margin-bottom: 15px; color: red; }

        /* Special Styling for the Printed Invoice */
        .invoice { background-color: #f8f9fa; border: 1px solid #ddd; padding: 20px; border-radius: 5px; margin-top: 25px; }
        .invoice h3 { margin-top: 0; color: #28a745; text-align: center; border-bottom: 2px dashed #ccc; padding-bottom: 10px;}
        .invoice p { font-size: 16px; line-height: 1.5; margin: 8px 0;}
        .total { font-size: 20px; font-weight: bold; color: #d9534f; border-top: 2px dashed #ccc; padding-top: 10px; margin-top: 10px;}
        .print-btn { background-color: #28a745; }
        .print-btn:hover { background-color: #218838; }
    </style>
</head>
<body>
<div class="container">
    <h2>Generate Electricity Bill</h2>

    <% if(request.getAttribute("error") != null) { %>
    <div class="error-message"><%= request.getAttribute("error") %></div>
    <% } %>

    <form action="BillingServlet" method="post">
        <div class="form-group">
            <label for="telephone">Customer Telephone Number:</label>
            <input type="text" id="telephone" name="telephone" required placeholder="Enter phone number">
        </div>
        <button type="submit">Search & Calculate</button>
        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    </form>

    <%
        // Grab the calculated data sent back from our Java Servlet
        org.example.model.Customer customer = (org.example.model.Customer) request.getAttribute("customerDetails");
        Double billAmount = (Double) request.getAttribute("billAmount");

        if(customer != null && billAmount != null) {
    %>
    <div class="invoice">
        <h3>Official Invoice</h3>
        <p><strong>Account Name:</strong> <%= customer.getName() %></p>
        <p><strong>Address:</strong> <%= customer.getAddress() %></p>
        <p><strong>Telephone:</strong> <%= customer.getTelephoneNumber() %></p>
        <p><strong>Units Consumed:</strong> <%= customer.getUnitsConsumed() %></p>

        <p class="total">Total Amount Due: Rs. <%= String.format("%.2f", billAmount) %></p>

        <button class="print-btn" onclick="window.print()">🖨️ Print Bill</button>
    </div>
    <% } %>

</div>
</body>
</html>