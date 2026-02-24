<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // SECURE ACCESS: This Java code checks if the user is actually logged in.
    // If they try to type "localhost:8080/dashboard.jsp" without logging in, it kicks them out!
    if(session.getAttribute("activeUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Pahana Edu - Main Menu</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; }
        .header { background-color: #0056b3; color: white; padding: 15px 20px; text-align: center; }
        .nav-container { display: flex; flex-wrap: wrap; justify-content: center; padding: 40px; gap: 20px; }
        .card { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); width: 250px; text-align: center; }
        .card h3 { color: #333; }
        .btn { display: inline-block; padding: 10px 20px; background-color: #0056b3; color: white; text-decoration: none; border-radius: 4px; margin-top: 10px; width: 80%; transition: background-color 0.3s;}
        .btn:hover { background-color: #004494; }
        .logout-btn { background-color: #dc3545; }
        .logout-btn:hover { background-color: #c82333; }
    </style>
</head>
<body>

<div class="header">
    <h1>Pahana Edu - Main Menu</h1>
    <p>Welcome, <%= ((org.example.model.User)session.getAttribute("activeUser")).getUsername() %></p>
</div>

<div class="nav-container">
    <div class="card">
        <h3>Customer Management</h3>
        <p>Add new customers or edit existing account details.</p>
        <a href="customer.jsp" class="btn">Manage Customers</a>
    </div>

    <div class="card">
        <h3>Item Management</h3>
        <p>Add, update, or delete inventory items.</p>
        <a href="item.jsp" class="btn">Manage Items</a>
    </div>

    <div class="card">
        <h3>Billing System</h3>
        <p>Calculate bill amounts based on units consumed.</p>
        <a href="billing.jsp" class="btn">Generate Bill</a>
    </div>

    <div class="card">
        <h3>System Help</h3>
        <p>View system usage guidelines for new users.</p>
        <a href="help.jsp" class="btn">Help Section</a>
    </div>

    <div class="card">
        <h3>Exit System</h3>
        <p>Securely log out and close the application.</p>
        <a href="LogoutServlet" class="btn logout-btn">Logout / Exit</a>
    </div>
</div>

</body>
</html>