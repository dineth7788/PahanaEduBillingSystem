<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedubookshop.model.Customer" %>
<%
    // Secure Access Check
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    com.pahanaedubookshop.model.User currentUser = (com.pahanaedubookshop.model.User) session.getAttribute("loggedUser");

    // Get data passed from the Servlet
    List<Customer> customerList = (List<Customer>) request.getAttribute("customerList");
    Customer editCus = (Customer) request.getAttribute("customerToEdit");
    boolean isEdit = (editCus != null);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Customers</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<div class="dashboard-layout">

    <aside class="sidebar">
        <div class="sidebar-header">Dashboard</div>
        <ul class="sidebar-menu">
            <li><a href="admin-dashboard.jsp"><i class="fa-solid fa-home"></i> Home</a></li>
            <li><a href="customers" class="active"><i class="fa-solid fa-user-group"></i> Customers</a></li>
            <li><a href="items"><i class="fa-solid fa-box"></i> Items</a></li>
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
            <h2><i class="fa-solid fa-user-gear"></i> Manage Customers</h2>
            <span class="total-badge">Total: <%= customerList != null ? customerList.size() : 0 %></span>
        </div>

        <div class="form-card">
            <div class="form-header">
                <i class="fa-solid <%= isEdit ? "fa-user-pen" : "fa-user-plus" %>"></i>
                <%= isEdit ? "Edit Customer Details" : "Register New Customer" %>
            </div>

            <form action="customers" method="post">
                <input type="hidden" name="customerId" value="<%= isEdit ? editCus.getCustomerId() : "" %>">

                <div class="form-body">
                    <div class="form-grid">
                        <div class="form-group">
                            <label>Account Number</label>
                            <input type="text" name="accountNumber" value="<%= isEdit ? editCus.getAccountNumber() : "" %>" placeholder="e.g. ACC-1001" required>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="name" value="<%= isEdit ? editCus.getName() : "" %>" placeholder="Full Name" required>
                        </div>
                        <div class="form-group">
                            <label>Telephone Number</label>
                            <input type="text" name="phone" value="<%= isEdit ? editCus.getPhone() : "" %>" placeholder="07XXXXXXXX" required>
                        </div>
                        <div class="form-group">
                            <label>Units Consumed</label>
                            <input type="number" name="unitsConsumed" value="<%= isEdit ? editCus.getUnitsConsumed() : "0" %>" min="0" required>
                        </div>
                        <div class="form-group full-width">
                            <label>Address</label>
                            <textarea name="address" style="min-height: 80px; resize: vertical;" required placeholder="Enter full address"><%= isEdit ? editCus.getAddress() : "" %></textarea>
                        </div>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn-primary">
                        <i class="fa-solid <%= isEdit ? "fa-save" : "fa-user-plus" %>"></i>
                        <%= isEdit ? "Update Customer" : "Add Customer" %>
                    </button>
                    <a href="customers">
                        <button type="button" class="btn-secondary">
                            <i class="fa-solid <%= isEdit ? "fa-xmark" : "fa-rotate-right" %>"></i>
                            <%= isEdit ? "Cancel Edit" : "Clear Form" %>
                        </button>
                    </a>
                </div>
            </form>
        </div>

        <h3><i class="fa-solid fa-list"></i> Customer Directory</h3>
        <table class="data-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Account No</th>
                <th>Telephone</th>
                <th>Units</th>
                <th>Address</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% if (customerList != null && !customerList.isEmpty()) {
                for (Customer c : customerList) { %>
            <tr>
                <td><%= c.getCustomerId() %></td>
                <td><strong><%= c.getName() %></strong></td>
                <td><%= c.getAccountNumber() %></td>
                <td><%= c.getPhone() %></td>
                <td><%= c.getUnitsConsumed() %></td>
                <td><%= c.getAddress() %></td>
                <td>
                    <a href="customers?action=edit&id=<%= c.getCustomerId() %>" class="action-btn edit-btn"><i class="fa-solid fa-pen-to-square"></i> Edit</a>
                    <a href="customers?action=delete&id=<%= c.getCustomerId() %>" class="action-btn delete-btn" onclick="return confirm('Are you sure you want to delete <%= c.getName() %>?');"><i class="fa-solid fa-trash"></i></a>
                </td>
            </tr>
            <% }} else { %>
            <tr>
                <td colspan="7" style="text-align: center; color: #888;">No customers registered yet.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </main>
</div>
</body>
</html>