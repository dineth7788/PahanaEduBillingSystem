<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>System Interrupt</title>
    <link rel="stylesheet" href="index.css">
</head>
<body style="display:flex; justify-content:center; align-items:center; height:100vh; background:#2c3e50;">
<div style="background:white; padding:40px; border-radius:8px; text-align:center; max-width:500px;">
    <h1 style="color:#e74c3c; font-size:48px; margin-bottom:10px;">⚠️</h1>
    <h2 style="color:#c0392b; margin-bottom:20px;">System Exception Encountered</h2>

    <p style="color:#7f8c8d; margin-bottom:30px;">
        <%= request.getAttribute("issueMsg") != null ? request.getAttribute("issueMsg") : "An unexpected server routing error occurred." %>
    </p>

    <button onclick="window.history.back()" class="action-btn">Return to Previous Screen</button>
</div>
</body>
</html>