<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedubookshop.Model.Book" %>
<%
    Book currentBook = (Book) request.getAttribute("bookItem");
    boolean isUpdate = (currentBook != null);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><%= isUpdate ? "Modify Product" : "Register Product" %></title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<header class="sys-header">
    <div class="brand-logo">Inventory Management</div>
    <nav class="top-nav"><a href="admin.jsp">Workspace</a></nav>
</header>

<main class="form-panel">
    <h2><%= isUpdate ? "Modify Existing Product" : "Register New Product" %></h2>
    <form action="<%= request.getContextPath() %>/manage-books" method="post">
        <input type="hidden" name="bookId" value="<%= isUpdate ? currentBook.getProductId() : "" %>">

        <div class="input-group">
            <label>Product Title:</label>
            <input type="text" name="bookTitle" required value="<%= isUpdate ? currentBook.getProductTitle() : "" %>">
        </div>

        <div class="input-group">
            <label>Product Description:</label>
            <textarea name="bookDesc" required rows="4"><%= isUpdate ? currentBook.getProductDesc() : "" %></textarea>
        </div>

        <div class="input-group">
            <label>Retail Price (Rs.):</label>
            <input type="number" step="0.01" name="bookPrice" required value="<%= isUpdate ? currentBook.getProductPrice() : "" %>">
        </div>

        <button type="submit" class="action-btn"><%= isUpdate ? "Save Modifications" : "Add to Inventory" %></button>
        <div style="text-align:center; margin-top:15px;">
            <a href="${pageContext.request.contextPath}/manage-books" style="color:#7f8c8d; text-decoration:none;">Cancel & Return</a>
        </div>
    </form>
</main>
</body>
</html>