<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Auth - Smart Edu</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<div class="form-panel" style="margin-top: 100px;">
    <h2>Secure Admin Login</h2>
    <form id="auth-form" method="post" action="${pageContext.request.contextPath}/adminLogin">
        <div class="input-group">
            <label for="username">System Username</label>
            <input type="text" id="username" name="username" required autocomplete="off">
        </div>
        <div class="input-group">
            <label for="password">Security Key</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="action-btn">Authenticate</button>
    </form>

    <c:if test="${not empty authError}">
        <p style="color:#e74c3c; margin-top:15px; font-weight:bold; text-align:center;">${authError}</p>
    </c:if>
</div>
<script src="index.js"></script>
</body>
</html>