<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
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
    <title>Place Order - Pahana Edu</title>
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

        <h2 class="print-hide" style="color: #1877f2; text-align: center; margin-bottom: 30px;">Place Order</h2>

        <form action="order" method="post" id="orderForm">

            <div class="form-card print-hide">
                <div class="form-header">Select Customer</div>
                <div class="form-body">
                    <select name="customerId" id="customerId" class="form-group" style="width: 100%; padding: 10px;" required>
                        <option value="">Select a customer...</option>
                        <c:forEach var="c" items="${customers}">
                            <option value="${c.customerId}">${c.name} (${c.accountNumber})</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-card print-hide">
                <div class="form-header">Add Items to Order</div>
                <div class="form-body form-grid-items" style="align-items: end;">
                    <div class="form-group" style="margin: 0;">
                        <label>Item</label>
                        <select id="itemSelect" style="padding: 10px; width: 100%;" onchange="updatePrice()">
                            <option value="" data-price="0">Select an item...</option>
                            <c:forEach var="item" items="${items}">
                                <option value="${item.itemId}" data-price="${item.price}" data-name="${item.itemName}">
                                        ${item.itemName} (Stock: ${item.quantity})
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group" style="margin: 0;">
                        <label>Quantity</label>
                        <input type="number" id="qtyInput" value="1" min="1" style="width: 100%;">
                    </div>
                    <div class="form-group" style="margin: 0;">
                        <label>Unit Price (Rs)</label>
                        <input type="text" id="priceInput" readonly style="width: 100%; background: #f0f0f0;">
                    </div>
                    <button type="button" class="btn-primary" onclick="addToCart()"><i class="fa-solid fa-plus"></i> Add</button>
                </div>
            </div>

            <table class="data-table print-hide" id="cartTable">
                <thead>
                <tr>
                    <th>Item</th>
                    <th>Quantity</th>
                    <th>Unit Price</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="cartBody">
                </tbody>
            </table>

            <div id="hiddenInputsContainer"></div>

            <div class="cart-summary-box print-hide">
                <div class="cart-total">
                    <h3>Total Amount</h3>
                    <h2 id="netTotalDisplay">Rs. 0.00</h2>
                </div>
                <button type="submit" class="btn-success" id="placeOrderBtn" disabled>
                    <i class="fa-regular fa-circle-check"></i> Place Order
                </button>
            </div>
        </form>
    </main>
</div>

<script>
    let netTotal = 0;

    function updatePrice() {
        const select = document.getElementById("itemSelect");
        const price = select.options[select.selectedIndex].getAttribute("data-price");
        document.getElementById("priceInput").value = parseFloat(price).toFixed(2);
    }

    function addToCart() {
        const select = document.getElementById("itemSelect");
        if (select.value === "") return alert("Please select an item first.");

        const itemId = select.value;
        const itemName = select.options[select.selectedIndex].getAttribute("data-name");
        const price = parseFloat(select.options[select.selectedIndex].getAttribute("data-price"));
        const qty = parseInt(document.getElementById("qtyInput").value);
        const lineTotal = price * qty;

        // Add to visual table
        const tbody = document.getElementById("cartBody");
        const rowId = 'row_' + Date.now();
        tbody.innerHTML += `
            <tr id="\${rowId}">
                <td>\${itemName}</td>
                <td>\${qty}</td>
                <td>Rs. \${price.toFixed(2)}</td>
                <td>Rs. \${lineTotal.toFixed(2)}</td>
                <td><button type="button" class="action-btn delete-btn" onclick="removeFromCart('\${rowId}', \${lineTotal})"><i class="fa-solid fa-trash"></i></button></td>
            </tr>
        `;

        // Add hidden inputs for Java
        const hiddenContainer = document.getElementById("hiddenInputsContainer");
        hiddenContainer.innerHTML += `
            <div id="hidden_\${rowId}">
                <input type="hidden" name="itemIds" value="\${itemId}">
                <input type="hidden" name="quantities" value="\${qty}">
                <input type="hidden" name="unitPrices" value="\${price}">
            </div>
        `;

        // Update Total
        netTotal += lineTotal;
        document.getElementById("netTotalDisplay").innerText = "Rs. " + netTotal.toFixed(2);

        // Enable Place Order Button
        document.getElementById("placeOrderBtn").disabled = false;

        // Reset inputs
        select.value = "";
        document.getElementById("qtyInput").value = 1;
        document.getElementById("priceInput").value = "";
    }

    function removeFromCart(rowId, lineTotal) {
        document.getElementById(rowId).remove();
        document.getElementById('hidden_' + rowId).remove();

        netTotal -= lineTotal;
        document.getElementById("netTotalDisplay").innerText = "Rs. " + Math.abs(netTotal).toFixed(2);

        if (netTotal <= 0) document.getElementById("placeOrderBtn").disabled = true;
    }
</script>
</body>
</html>