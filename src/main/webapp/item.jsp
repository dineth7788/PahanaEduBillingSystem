<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Security check to ensure user is logged in
    if(session.getAttribute("activeUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Pahana Edu - Manage Items</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; }
        .container { background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        h2 { color: #333; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; color: #666; font-weight: bold;}
        input[type="text"], input[type="number"] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #17a2b8; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; margin-top: 10px;}
        button:hover { background-color: #138496; }
        .back-btn { background-color: #6c757d; display: block; text-align: center; text-decoration: none; margin-top: 10px; padding: 10px; color: white; border-radius: 4px; }
        .back-btn:hover { background-color: #5a6268; }
        .message { text-align: center; font-weight: bold; margin-bottom: 15px; color: #0056b3; }
    </style>
</head>
<body>
<div class="container">
    <h2>Add New Inventory Item</h2>

    <% if(request.getAttribute("message") != null) { %>
    <div class="message"><%= request.getAttribute("message") %></div>
    <% } %>

    <form action="ItemServlet" method="post">
        <div class="form-group">
            <label for="itemName">Item Name (Book Title, Stationery, etc.):</label>
            <input type="text" id="itemName" name="itemName" required>
        </div>
        <div class="form-group">
            <label for="price">Price (Rs.):</label>
            <input type="number" id="price" name="price" step="0.01" min="0" required>
        </div>
        <div class="form-group">
            <label for="stock">Stock Quantity:</label>
            <input type="number" id="stock" name="stock" min="0" required>
        </div>
        <button type="submit">Save Item</button>
        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    </form>
</div>
</body>
</html>