<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedubookshop.Model.Customer" %>
<%
    Customer clientObj = (Customer) request.getAttribute("clientData");
    boolean isMod = (clientObj != null);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><%= isMod ? "Update Client" : "Register Client" %></title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<header class="sys-header">
    <div class="brand-logo">Client Management</div>
    <nav class="top-nav"><a href="admin.jsp">Workspace</a></nav>
</header>

<main class="form-panel">
    <h2><%= isMod ? "Modify Member File" : "New Member Registration" %></h2>
    <form action="${pageContext.request.contextPath}/customers" method="post">
        <input type="hidden" name="clientId" value="<%= isMod ? clientObj.getClientId() : "" %>">

        <div class="input-group">
            <label>Member Access Code:</label>
            <input type="text" name="clientAccNo" required value="<%= isMod ? clientObj.getClientAccNo() : "" %>" placeholder="e.g. MEM-001">
        </div>
        <div class="input-group">
            <label>Full Legal Name:</label>
            <input type="text" name="clientName" required value="<%= isMod ? clientObj.getClientName() : "" %>">
        </div>
        <div class="input-group">
            <label>Age:</label>
            <input type="number" name="clientAge" required value="<%= isMod ? clientObj.getClientAge() : "" %>">
        </div>
        <div class="input-group">
            <label>Physical Address:</label>
            <input type="text" name="clientAddress" required value="<%= isMod ? clientObj.getClientAddress() : "" %>">
        </div>
        <div class="input-group">
            <label>Email Contact:</label>
            <input type="email" name="clientEmail" required value="<%= isMod ? clientObj.getClientEmail() : "" %>">
        </div>
        <div class="input-group">
            <label>Primary Phone:</label>
            <input type="text" name="clientPhone" required value="<%= isMod ? clientObj.getClientPhone() : "" %>">
        </div>

        <button type="submit" class="action-btn"><%= isMod ? "Apply Changes" : "Register Member" %></button>
        <div style="text-align:center; margin-top:15px;">
            <a href="${pageContext.request.contextPath}/customers" style="color:#7f8c8d; text-decoration:none;">Cancel & Return</a>
        </div>
    </form>
</main>
</body>
</html>