<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedubookshop.Model.Customer" %>
<% List<Customer> clients = (List<Customer>) request.getAttribute("clientList"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Client Directory</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<header class="sys-header">
    <div class="brand-logo">Client Directory</div>
    <nav class="top-nav">
        <a href="${pageContext.request.contextPath}/customers?action=create" style="background:#2ecc71; padding:5px 10px; border-radius:4px;">+ Register Client</a>
        <a href="admin.jsp">Workspace</a>
    </nav>
</header>

<main style="padding: 20px; max-width: 1200px; margin: 0 auto;">
    <table class="data-table">
        <thead>
        <tr>
            <th>Mem. Code</th><th>Full Name</th><th>Age</th><th>Location</th><th>Email</th><th>Contact</th><th>Controls</th>
        </tr>
        </thead>
        <tbody>
        <% if (clients != null && !clients.isEmpty()) {
            for (Customer c : clients) { %>
        <tr>
            <td><strong><%= c.getClientAccNo() %></strong></td>
            <td><%= c.getClientName() %></td>
            <td><%= c.getClientAge() %></td>
            <td><%= c.getClientAddress() %></td>
            <td><%= c.getClientEmail() %></td>
            <td><%= c.getClientPhone() %></td>
            <td>
                <a href="${pageContext.request.contextPath}/customers?action=modify&id=<%= c.getClientId() %>" style="color:#f39c12; text-decoration:none;">Edit</a> |
                <a href="${pageContext.request.contextPath}/customers?action=erase&id=<%= c.getClientId() %>" onclick="return confirm('Erase this client record?')" style="color:#e74c3c; text-decoration:none;">Del</a>
            </td>
        </tr>
        <% }} else { %>
        <tr><td colspan="7" style="text-align:center;">No clients registered.</td></tr>
        <% } %>
        </tbody>
    </table>
</main>
</body>
</html>