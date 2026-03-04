<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp"); return;
    }
    // Added this line so the header can successfully read the username!
    com.pahanaedubookshop.model.User currentUser = (com.pahanaedubookshop.model.User) session.getAttribute("loggedUser");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Invoice #${bill.billId}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<div class="dashboard-layout">

    <aside class="sidebar print-hide">
        <div class="sidebar-header">Dashboard</div>
        <ul class="sidebar-menu">
            <li><a href="admin-dashboard.jsp"><i class="fa-solid fa-home"></i> Home</a></li>
            <li><a href="customers"><i class="fa-solid fa-user-group"></i> Customers</a></li>
            <li><a href="items"><i class="fa-solid fa-box"></i> Items</a></li>
            <li><a href="order" class="active"><i class="fa-solid fa-file-invoice-dollar"></i> Place Order</a></li>
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

        <c:if test="${param.success == 'true'}">
            <div class="success-toast print-hide">
                <span><i class="fa-solid fa-circle-check"></i> Order placed successfully!</span>
            </div>
        </c:if>

        <div class="manage-header print-hide">
            <h2>Order Successful - Bill Generated</h2>
            <div>
                <button class="btn-secondary" onclick="window.print()"><i class="fa-solid fa-print"></i> Print Bill</button>
                <a href="order"><button class="btn-secondary"><i class="fa-solid fa-plus"></i> New Order</button></a>
            </div>
        </div>

        <div class="invoice-card">
            <div class="invoice-header">
                <h1>PAHANA EDU MANAGEMENT</h1>
                <p>Pahana EDU Invoice</p>
            </div>

            <div class="invoice-details">
                <div>
                    <strong>Bill To:</strong><br>
                    <span style="font-size: 18px; font-weight: bold;">${customer.name}</span><br>
                    Account: ${customer.accountNumber}<br>
                    Phone: ${customer.phone}<br>
                    Address: ${customer.address}
                </div>
                <div style="text-align: right;">
                    <strong>Bill Details:</strong><br>
                    Bill ID: <strong>#${bill.billId}</strong><br>
                    Date: <fmt:formatDate value="${bill.billDate}" pattern="MM/dd/yyyy HH:mm" /><br>
                    Status: <span class="status-paid">Paid</span>
                </div>
            </div>

            <table class="invoice-table">
                <thead>
                <tr>
                    <th>Item</th>
                    <th style="text-align: center;">Quantity</th>
                    <th style="text-align: right;">Unit Price</th>
                    <th style="text-align: right;">Total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${bill.items}">
                    <tr>
                        <td>${item.itemName}</td>
                        <td style="text-align: center;">${item.quantity}</td>
                        <td style="text-align: right;">$<fmt:formatNumber value="${item.unitPrice}" type="number" minFractionDigits="2"/></td>
                        <td style="text-align: right;">$<fmt:formatNumber value="${item.totalPrice}" type="number" minFractionDigits="2"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="invoice-total">
                Grand Total: $<fmt:formatNumber value="${bill.totalAmount}" type="number" minFractionDigits="2"/>
            </div>
        </div>

    </main>
</div>
</body>
</html>