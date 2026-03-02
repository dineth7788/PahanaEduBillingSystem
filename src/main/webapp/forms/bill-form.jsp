<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedubookshop.Model.*" %>
<%
    List<Customer> clientList = (List<Customer>) request.getAttribute("clients");
    List<Book> productList = (List<Book>) request.getAttribute("products");
    String sysIssue = (String) request.getAttribute("issueMsg");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>POS Terminal</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<header class="sys-header">
    <div class="brand-logo">Point of Sale (POS)</div>
    <nav class="top-nav"><a href="admin.jsp">Workspace</a></nav>
</header>

<main style="max-width: 900px; margin: 30px auto; padding: 20px;">
    <% if (sysIssue != null) { %>
    <div style="background:#ff7675; color:white; padding:15px; border-radius:5px; margin-bottom:20px; font-weight:bold;">
        System Notice: <%= sysIssue %>
    </div>
    <% } %>

    <div class="form-panel" style="max-width:100%; margin:0;">
        <div style="background:#f8f9fa; padding:20px; border-radius:5px; margin-bottom:25px; border:1px solid #ddd;">
            <h3 style="color:#2c3e50; margin-bottom:10px;">Step 1: Client Verification</h3>
            <div style="display:flex; gap:10px;">
                <input type="text" id="memberCode" placeholder="Scan or enter Member Code (e.g. MEM-001)" style="flex:1; padding:10px; border:1px solid #ccc; border-radius:4px;">
                <button type="button" onclick="verifyClient()" class="action-btn" style="width:auto;">Verify</button>
            </div>

            <div id="verifiedPanel" style="display:none; margin-top:15px; padding:15px; background:#d4edda; border-left:4px solid #28a745; color:#155724;">
                <p id="clientDataDisplay"></p>
            </div>
        </div>

        <form action="bill" method="post" id="invoiceForm">
            <input type="hidden" id="targetClientId" name="targetClientId">

            <h3 style="color:#2c3e50; margin-bottom:10px;">Step 2: Order Details</h3>
            <table class="data-table">
                <thead>
                <tr>
                    <th>Product Title</th>
                    <th>Unit Price</th>
                    <th style="width:150px;">Order Qty</th>
                </tr>
                </thead>
                <tbody>
                <% if(productList != null) { for(Book p : productList) { %>
                <tr>
                    <td><%= p.getProductTitle() %></td>
                    <td>Rs. <%= String.format("%.2f", p.getProductPrice()) %></td>
                    <td><input type="number" name="orderQty_<%= p.getProductId() %>" value="0" min="0" style="width:100%; padding:5px;"></td>
                </tr>
                <% }} %>
                </tbody>
            </table>

            <button type="button" onclick="submitInvoice()" class="action-btn" style="background:#2ecc71; margin-top:20px; font-size:1.1rem;">Process Transaction</button>
        </form>
    </div>
</main>

<script>
    function verifyClient() {
        const code = document.getElementById('memberCode').value.trim();
        if(!code) { alert('Enter Member Code'); return; }

        fetch('bill?action=findClient&memberCode=' + encodeURIComponent(code))
            .then(res => res.json())
            .then(data => {
                if(data.status) {
                    document.getElementById('clientDataDisplay').innerHTML =
                        '<strong>Verified Member:</strong> ' + data.clientDetails.name + '<br>' +
                        '<strong>Contact:</strong> ' + data.clientDetails.phone;
                    document.getElementById('targetClientId').value = data.clientDetails.id;
                    document.getElementById('verifiedPanel').style.display = 'block';
                } else {
                    alert(data.info || 'Client not found in database.');
                    document.getElementById('verifiedPanel').style.display = 'none';
                    document.getElementById('targetClientId').value = '';
                }
            }).catch(e => alert('System routing error.'));
    }

    function submitInvoice() {
        if(!document.getElementById('targetClientId').value) {
            alert("Security Lock: You must verify a client before processing an invoice.");
            return;
        }
        document.getElementById('invoiceForm').submit();
    }
</script>
</body>
</html>