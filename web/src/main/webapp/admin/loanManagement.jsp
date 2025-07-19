<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Loan Management - SL National Bank</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: #f2f6fc;
            color: #202640;
            padding: 1.5rem;
        }
        h1 {
            color: #1e40af;
            text-align: center;
            margin-bottom: 2rem;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            box-shadow: 0 6px 15px rgb(30 64 175 / 0.15);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 0.75rem 1rem;
            text-align: left;
        }
        th {
            background-color: #3b82f6;
            color: white;
        }
        tr:hover {
            background-color: #dbeafe;
        }
        button {
            padding: 0.4rem 0.9rem;
            margin: 0 0.2rem;
            border: none;
            border-radius: 6px;
            font-weight: bold;
            cursor: pointer;
            color: white;
        }
        .approve-btn { background-color: #22c55e; }
        .reject-btn { background-color: #ef4444; }
        .approve-btn:hover { background-color: #16a34a; }
        .reject-btn:hover { background-color: #dc2626; }
    </style>

    <script src="${pageContext.request.contextPath}/js/loanManagement.js" defer></script>
</head>
<body>

<h1>Loan Management - Approve or Reject Loans</h1>

<table aria-label="Loan applications">
    <thead>
    <tr>
        <th>User Name</th>
        <th>Email</th>
        <th>Account No</th>
        <th>Loan ID</th>
        <th>Loan Amount</th>
        <th>Status</th>
        <th>Requested Date</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody id="loanTableBody">
    <!-- JavaScript will populate this -->
    </tbody>
</table>

</body>
</html>
