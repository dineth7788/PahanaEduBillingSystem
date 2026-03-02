<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>System Documentation</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>

<header class="sys-header">
    <div class="brand-logo">Documentation</div>
    <nav class="top-nav">
        <a href="admin.jsp">Return to Workspace</a>
    </nav>
</header>

<main style="max-width: 800px; margin: 40px auto; background: white; padding: 40px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.05);">
    <h1 style="border-bottom: 2px solid #3498db; padding-bottom: 10px; margin-bottom: 20px;">System Administrator Guidelines</h1>

    <div style="margin-bottom: 25px;">
        <h3 style="color: #3498db;">1. Inventory Control (Books)</h3>
        <p>Ensure all product titles, descriptions, and pricing are accurate before saving. When updating prices, ensure existing pending carts are closed first.</p>
    </div>

    <div style="margin-bottom: 25px;">
        <h3 style="color: #3498db;">2. Client Management</h3>
        <p>The Member Code must be uniquely assigned to each customer to avoid database duplication errors during checkout.</p>
    </div>

    <div style="margin-bottom: 25px;">
        <h3 style="color: #3498db;">3. POS & Invoicing</h3>
        <p>The Point of Sale (POS) system requires a valid Client Member Code before processing an invoice. Ensure quantities are set above zero.</p>
    </div>

    <div style="margin-top: 40px; padding-top: 20px; border-top: 1px solid #ecf0f1;">
        <p><strong>Technical Support:</strong> For system database errors, contact IT Administration at admin@smartedu.local.</p>
    </div>
</main>

</body>
</html>