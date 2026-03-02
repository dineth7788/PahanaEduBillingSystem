<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedubookshop.Model.Book" %>
<% List<Book> inventory = (List<Book>) request.getAttribute("inventoryList"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Inventory Directory</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<header class="sys-header">
    <div class="brand-logo">Inventory Directory</div>
    <nav class="top-nav">
        <a href="${pageContext.request.contextPath}/manage-books?action=new" style="background:#2ecc71; padding:5px 10px; border-radius:4px;">+ Add Product</a>
        <a href="admin.jsp">Workspace</a>
    </nav>
</header>

<main style="padding: 20px; max-width: 1000px; margin: 0 auto;">
    <table class="data-table">
        <thead>
        <tr>
            <th>Ref ID</th><th>Title</th><th>Description</th><th>Price</th><th>Controls</th>
        </tr>
        </thead>
        <tbody>
        <% if(inventory != null && !inventory.isEmpty()) {
            for (Book item : inventory) { %>
        <tr>
            <td>#<%= item.getProductId() %></td>
            <td><strong><%= item.getProductTitle() %></strong></td>
            <td><%= item.getProductDesc() %></td>
            <td>Rs. <%= String.format("%.2f", item.getProductPrice()) %></td>
            <td>
                <a href="${pageContext.request.contextPath}/manage-books?action=update&id=<%= item.getProductId() %>" style="color:#f39c12; text-decoration:none; margin-right:10px;">Edit</a>
                <a href="${pageContext.request.contextPath}/manage-books?action=remove&id=<%= item.getProductId() %>" onclick="return confirm('Permanently delete this product?')" style="color:#e74c3c; text-decoration:none;">Drop</a>
            </td>
        </tr>
        <% }} else { %>
        <tr><td colspan="5" style="text-align:center;">No products currently in inventory.</td></tr>
        <% } %>
        </tbody>
    </table>
</main>
</body>
</html>