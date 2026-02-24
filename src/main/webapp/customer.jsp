<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Security check!
    if(session.getAttribute("activeUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Pahana Edu - Manage Customers</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; }
        .container { background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        h2 { color: #333; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; color: #666; font-weight: bold;}
        input[type="text"], input[type="number"] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; margin-top: 10px;}
        button:hover { background-color: #218838; }
        .back-btn { background-color: #6c757d; display: block; text-align: center; text-decoration: none; margin-top: 10px; padding: 10px; color: white; border-radius: 4px; }
        .back-btn:hover { background-color: #5a6268; }
        .message { text-align: center; font-weight: bold; margin-bottom: 15px; color: #0056b3; }
    </style>
</head>
<body>
<div class="container">
    <h2>Add New Customer</h2>

    <% if(request.getAttribute("message") != null) { %>
    <div class="message"><%= request.getAttribute("message") %></div>
    <% } %>

    <form action="CustomerServlet" method="post">
        <div class="form-group">
            <label for="name">Customer Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>
        </div>
        <div class="form-group">
            <label for="telephone">Telephone Number:</label>
            <input type="text" id="telephone" name="telephone" required>
        </div>
        <div class="form-group">
            <label for="units">Units Consumed:</label>
            <input type="number" id="units" name="units" min="0" required>
        </div>
        <button type="submit">Save Customer</button>
        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    </form>
</div>
</body>
</html>