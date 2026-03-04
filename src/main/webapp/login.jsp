<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In - Pahana Edu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<div class="login-card">
    <h2 class="login-title">Sign In</h2>

    <%-- We will map this action to our Java Servlet later --%>
    <form action="${pageContext.request.contextPath}/auth" method="post">

        <%-- Error message display area --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="error-msg"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <div class="input-group">
            <input type="text" name="username" placeholder="User Name" required autocomplete="off">
        </div>

        <div class="input-group">
            <input type="password" id="password" name="password" placeholder="Password" required>
            <i class="fa-solid fa-eye-slash toggle-password" id="togglePassword" onclick="toggleVisibility()"></i>
        </div>

        <div class="remember-me">
            <input type="checkbox" id="remember" name="remember">
            <label for="remember">Remember me</label>
        </div>

        <button type="submit" class="sign-in-btn">Sign in</button>
    </form>

    <div class="register-link">
        Don't have an account? <a href="register.jsp">Register now</a>
    </div>
</div>

<script>
    // Simple script to make the password eye icon work
    function toggleVisibility() {
        const passwordInput = document.getElementById("password");
        const icon = document.getElementById("togglePassword");

        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            icon.classList.remove("fa-eye-slash");
            icon.classList.add("fa-eye");
        } else {
            passwordInput.type = "password";
            icon.classList.remove("fa-eye");
            icon.classList.add("fa-eye-slash");
        }
    }
</script>
</body>
</html>