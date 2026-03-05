<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedubookshop.dao.DashboardDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedubookshop.model.Item" %>
<%
    // SECURE ACCESS
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    com.pahanaedubookshop.model.User currentUser = (com.pahanaedubookshop.model.User) session.getAttribute("loggedUser");

    // Fetch live data from the database
    DashboardDAO dashboardDAO = new DashboardDAO();
    int totalCustomers = dashboardDAO.getTotalCustomers();
    int totalOrders = dashboardDAO.getTotalOrders();
    double totalRevenue = dashboardDAO.getTotalRevenue();

    // Fetch our new Low Stock feature
    List<Item> lowStockItems = dashboardDAO.getLowStockItems();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Pahana Edu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<div class="dashboard-layout">

    <aside class="sidebar">
        <div class="sidebar-header">
            Dashboard
        </div>
        <ul class="sidebar-menu">
            <li><a href="admin-dashboard.jsp" class="active"><i class="fa-solid fa-home"></i> Home</a></li>
            <li><a href="customers"><i class="fa-solid fa-user-group"></i> Customers</a></li>
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

        <div class="cards-grid">
            <div class="summary-card card-blue">
                <div class="card-info">
                    <h3>Total Customers</h3>
                    <h2><%= totalCustomers %></h2>
                    <p>Registered customers</p>
                </div>
                <div class="card-icon icon-blue"><i class="fa-solid fa-user-group"></i></div>
            </div>

            <div class="summary-card card-green">
                <div class="card-info">
                    <h3>Total Revenue</h3>
                    <h2>Rs. <%= String.format("%.2f", totalRevenue) %></h2>
                    <p>From all sales</p>
                </div>
                <div class="card-icon icon-green"><i class="fa-solid fa-rupee-sign"></i></div>
            </div>

            <div class="summary-card card-cyan">
                <div class="card-info">
                    <h3>Total Orders</h3>
                    <h2><%= totalOrders %></h2>
                    <p>Completed orders</p>
                </div>
                <div class="card-icon icon-cyan"><i class="fa-solid fa-receipt"></i></div>
            </div>
        </div>

        <div class="dashboard-widgets">

            <div class="widget-card">
                <div class="widget-header">
                    <i class="fa-solid fa-triangle-exclamation" style="color: #dc3545;"></i> Inventory Alerts
                </div>

                <% if (lowStockItems != null && !lowStockItems.isEmpty()) {
                    for (Item item : lowStockItems) { %>
                <div class="alert-row">
                    <span class="alert-name"><i class="fa-solid fa-book" style="color: #888; margin-right: 8px;"></i> <%= item.getItemName() %></span>
                    <span class="alert-qty"><%= item.getQuantity() %> Left</span>
                </div>
                <%  }
                } else { %>
                <p style="color: #198754; text-align: center; margin-top: 20px;">
                    <i class="fa-solid fa-circle-check"></i> All items are sufficiently stocked!
                </p>
                <% } %>
            </div>

            <div class="widget-card">
                <div class="widget-header">
                    <i class="fa-solid fa-bolt" style="color: #ffc107;"></i> Quick Actions
                </div>
                <a href="order" class="quick-action-btn"><i class="fa-solid fa-cart-plus"></i> New Order</a>
                <a href="customers" class="quick-action-btn"><i class="fa-solid fa-user-plus"></i> Add Customer</a>
                <a href="items" class="quick-action-btn"><i class="fa-solid fa-box-open"></i> Update Stock</a>
            </div>

        </div>

    </main>
</div>

</body>
</html>