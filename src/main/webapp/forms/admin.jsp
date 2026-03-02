<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Basic session security check matching your new session variable
    if(session.getAttribute("loggedAdmin") == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Workspace</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>

<header class="sys-header">
    <div class="brand-logo">Admin Workspace</div>
    <nav class="top-nav">
        <a href="../index.jsp" style="color: #e74c3c;">Secure Logout</a>
    </nav>
</header>

<main>
    <h2 style="text-align: center; margin-top: 30px; color: #2c3e50;">System Control Center</h2>

    <div class="grid-dashboard">
        <div class="dash-card" onclick="location.href='<%=request.getContextPath()%>/manage-books?action=new';">
            <h2>📘</h2>
            <h3>Register Book</h3>
            <p>Add new titles to inventory</p>
        </div>

        <div class="dash-card" onclick="location.href='<%=request.getContextPath()%>/manage-books';">
            <h2>📚</h2>
            <h3>Inventory Table</h3>
            <p>View & modify existing books</p>
        </div>

        <div class="dash-card" onclick="location.href='<%=request.getContextPath()%>/customers?action=create';">
            <h2>👤</h2>
            <h3>New Client</h3>
            <p>Register a store member</p>
        </div>

        <div class="dash-card" onclick="location.href='<%=request.getContextPath()%>/customers';">
            <h2>👥</h2>
            <h3>Client Directory</h3>
            <p>Manage member records</p>
        </div>

        <div class="dash-card" onclick="location.href='<%=request.getContextPath()%>/bill';">
            <h2>🛒</h2>
            <h3>POS Terminal</h3>
            <p>Generate customer invoices</p>
        </div>

        <div class="dash-card" onclick="location.href='Help.jsp';">
            <h2>❓</h2>
            <h3>System Help</h3>
            <p>Admin guidelines</p>
        </div>
    </div>
</main>

<script src="index.js"></script>
</body>
</html>