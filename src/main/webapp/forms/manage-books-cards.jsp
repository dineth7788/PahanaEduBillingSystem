<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedubookshop.Model.Book" %>
<% List<Book> inventory = (List<Book>) request.getAttribute("inventoryList"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Visual Inventory</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<header class="sys-header">
    <div class="brand-logo">Visual Inventory</div>
    <nav class="top-nav"><a href="admin.jsp">Workspace</a></nav>
</header>

<main class="grid-dashboard">
    <% if (inventory != null && !inventory.isEmpty()) {
        for (Book b : inventory) { %>
    <div class="dash-card" onclick="location.href='${pageContext.request.contextPath}/manage-books?action=update&id=<%= b.getProductId() %>'">
        <h3 style="color:#2980b9;"><%= b.getProductTitle() %></h3>
        <p style="font-size:0.85rem; color:#7f8c8d; margin-top:10px;"><%= b.getProductDesc() %></p>
        <p style="font-weight:bold; color:#2c3e50; margin-top:15px;">Rs. <%= String.format("%.2f", b.getProductPrice()) %></p>
    </div>
    <% }} else { %>
    <p style="grid-column: 1/-1; text-align:center;">Inventory is empty.</p>
    <% } %>
</main>
</body>
</html>