<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedubookshop.model.Item" %>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    com.pahanaedubookshop.model.User currentUser = (com.pahanaedubookshop.model.User) session.getAttribute("loggedUser");

    List<Item> itemList = (List<Item>) request.getAttribute("itemList");
    Item editItem = (Item) request.getAttribute("itemToEdit");
    boolean isEdit = (editItem != null);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Items</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<div class="dashboard-layout">

    <aside class="sidebar">
        <div class="sidebar-header">Dashboard</div>
        <ul class="sidebar-menu">
            <li><a href="admin-dashboard.jsp"><i class="fa-solid fa-home"></i> Home</a></li>
            <li><a href="customers"><i class="fa-solid fa-user-group"></i> Customers</a></li>
            <li><a href="items" class="active"><i class="fa-solid fa-box"></i> Items</a></li>
            <li><a href="order"><i class="fa-solid fa-file-invoice-dollar"></i> Place Order</a></li>
            <li><a href="help.jsp"><i class="fa-regular fa-circle-question"></i> Help Section</a></li>
        </ul>
    </aside>

    <main class="main-content">
        <header class="top-header print-hide">
            <div class="user-profile">
                <i class="fa-solid fa-user-circle"></i>
                <%= currentUser.getUsername() %>
                <i class="fa-solid fa-caret-down" style="font-size: 12px; margin-left: 5px;"></i>

                <div class="dropdown-menu">
                    <a href="${pageContext.request.contextPath}/logout">
                        <i class="fa-solid fa-right-from-bracket"></i> Logout
                    </a>
                </div>
            </div>
        </header>

        <div class="manage-header">
            <h2><i class="fa-solid fa-box-open"></i> Manage Items</h2>
            <span class="total-badge">Total: <%= itemList != null ? itemList.size() : 0 %></span>
        </div>

        <div class="form-card">
            <div class="form-header">
                <i class="fa-solid <%= isEdit ? "fa-pen" : "fa-plus-circle" %>"></i>
                <%= isEdit ? "Edit Item Details" : "Add New Item" %>
            </div>

            <form action="items" method="post">
                <input type="hidden" name="itemId" value="<%= isEdit ? editItem.getItemId() : "" %>">

                <div class="form-body">
                    <div class="form-grid-items">
                        <div class="form-group">
                            <label>Item Name</label>
                            <input type="text" name="itemName" value="<%= isEdit ? editItem.getItemName() : "" %>" placeholder="Item Name" required>
                        </div>
                        <div class="form-group">
                            <label>Price (Rs.)</label>
                            <input type="number" step="0.01" min="0.01" name="price" value="<%= isEdit ? editItem.getPrice() : "" %>" placeholder="Price (Rs.)" required>
                        </div>
                        <div class="form-group">
                            <label>Quantity</label>
                            <input type="number" name="quantity" value="<%= isEdit ? editItem.getQuantity() : "" %>" placeholder="Quantity" min="1" required>
                        </div>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn-primary">
                        <i class="fa-solid <%= isEdit ? "fa-save" : "fa-plus-circle" %>"></i>
                        <%= isEdit ? "Update Item" : "Add Item" %>
                    </button>
                    <a href="items">
                        <button type="button" class="btn-secondary">
                            <i class="fa-solid <%= isEdit ? "fa-xmark" : "fa-rotate-right" %>"></i>
                            <%= isEdit ? "Cancel" : "Reset" %>
                        </button>
                    </a>
                </div>
            </form>
        </div>

        <div class="manage-header" style="margin-top: 30px;">
            <h3><i class="fa-solid fa-list-ul"></i> Item List</h3>
            <a href="items" class="refresh-btn"><i class="fa-solid fa-rotate-right"></i> Refresh</a>
        </div>
        <table class="data-table">
            <thead>
            <tr>
                <th>Item ID</th>
                <th>Item Name</th>
                <th>Price (Rs.)</th>
                <th>Quantity in Stock</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% if (itemList != null && !itemList.isEmpty()) {
                for (Item item : itemList) { %>
            <tr>
                <td><%= item.getItemId() %></td>
                <td><strong><%= item.getItemName() %></strong></td>
                <td>Rs. <%= String.format("%.2f", item.getPrice()) %></td>
                <td>
                        <span style="<%= item.getQuantity() == 0 ? "color: red; font-weight: bold;" : "" %>">
                            <%= item.getQuantity() %>
                        </span>
                </td>
                <td>
                    <a href="items?action=edit&id=<%= item.getItemId() %>" class="action-btn edit-btn"><i class="fa-solid fa-pen-to-square"></i></a>
                    <a href="items?action=delete&id=<%= item.getItemId() %>" class="action-btn delete-btn" onclick="return confirm('Are you sure you want to delete <%= item.getItemName() %>?');"><i class="fa-solid fa-trash"></i></a>
                </td>
            </tr>
            <% }} else { %>
            <tr>
                <td colspan="5" style="text-align: center; color: #888;">No items in inventory.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </main>
</div>
</body>
</html>