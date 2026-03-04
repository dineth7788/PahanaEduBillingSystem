<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // SECURE ACCESS: Check if the user is logged in
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    com.pahanaedubookshop.model.User currentUser = (com.pahanaedubookshop.model.User) session.getAttribute("loggedUser");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Help Section - Pahana Edu</title>
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
            <li><a href="items"><i class="fa-solid fa-box"></i> Items</a></li>
            <li><a href="order"><i class="fa-solid fa-file-invoice-dollar"></i> Place Order</a></li>
            <li><a href="help.jsp" class="active"><i class="fa-regular fa-circle-question"></i> Help Section</a></li>
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

        <div class="help-container">
            <div class="help-header">
                <h2><i class="fa-solid fa-circle-info"></i> System Usage Guidelines</h2>
                <p>Welcome to the Pahana Edu Support Center. Click on a topic below to learn how to use the system.</p>
            </div>

            <button class="accordion">1. How do I register a new customer? <i class="fa-solid fa-chevron-down"></i></button>
            <div class="panel">
                <p>To add a new customer to the database:</p>
                <ul>
                    <li>Navigate to the <strong>Customers</strong> tab using the left sidebar.</li>
                    <li>Fill out the "Register New Customer" form with their Account Number, Name, Telephone, Units Consumed, and Address.</li>
                    <li>Click the blue <strong>Add Customer</strong> button.</li>
                    <li>The new customer will immediately appear in the Customer Directory table below.</li>
                </ul>
            </div>

            <button class="accordion">2. How do I manage book inventory (Items)? <i class="fa-solid fa-chevron-down"></i></button>
            <div class="panel">
                <p>To keep track of your stock:</p>
                <ul>
                    <li>Click on the <strong>Items</strong> tab in the sidebar.</li>
                    <li>You can register new books by providing the Item Name, Price, and current Quantity.</li>
                    <li>If stock runs low, locate the item in the table and click the yellow <strong>Edit</strong> button to update the quantity.</li>
                </ul>
            </div>

            <button class="accordion">3. How do I place an order and generate a bill? <i class="fa-solid fa-chevron-down"></i></button>
            <div class="panel">
                <p>To process a customer's purchase:</p>
                <ul>
                    <li>Go to the <strong>Place Order</strong> section.</li>
                    <li>Select the registered customer from the dropdown menu.</li>
                    <li>Select an item, enter the quantity they are buying, and click <strong>Add</strong> to put it in the cart.</li>
                    <li>Once all items are added, click the green <strong>Place Order</strong> button.</li>
                    <li>You will be redirected to the Invoice page where you can click <strong>Print Bill</strong> to hand a physical copy to the customer.</li>
                </ul>
            </div>

            <button class="accordion">4. How do I edit or delete records? <i class="fa-solid fa-chevron-down"></i></button>
            <div class="panel">
                <p>Mistakes happen! Here is how to fix them:</p>
                <ul>
                    <li>In both the <strong>Customers</strong> and <strong>Items</strong> directories, every row has an "Actions" column.</li>
                    <li>Click the yellow <i class="fa-solid fa-pen-to-square"></i> icon to edit a record. The form at the top will load their data so you can change it and click Update.</li>
                    <li>Click the red <i class="fa-solid fa-trash"></i> icon to permanently delete a record. <em>Note: If a customer has associated bills, deleting them will also delete their bill history!</em></li>
                </ul>
            </div>

        </div>
    </main>
</div>

<script>
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function() {
            // Toggle active class for styling and arrow rotation
            this.classList.toggle("active");

            // Get the hidden panel right below the button
            var panel = this.nextElementSibling;

            // Toggle the max-height to trigger the slide-down animation
            if (panel.style.maxHeight) {
                panel.style.maxHeight = null;
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
            }
        });
    }
</script>
</body>
</html>