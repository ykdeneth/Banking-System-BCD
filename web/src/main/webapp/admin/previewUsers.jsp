<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>User Management - SL National Bank</title>

    <style>
        /* Basic styling */
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: #f5f7fa;
            margin: 0; padding: 1rem;
            color: #333;
        }
        h1 {
            text-align: center;
            color: #1E40AF;
            margin-bottom: 1rem;
            user-select: none;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
            margin: 0 auto;
        }
        thead {
            background-color: #3B82F6;
            color: white;
        }
        th, td {
            padding: 0.8rem 1rem;
            border-bottom: 1px solid #e2e8f0;
            text-align: left;
            vertical-align: middle;
        }
        tbody tr:hover {
            background-color: #e0e7ff;
            transition: background-color 0.25s ease;
        }
        .accounts-list {
            font-size: 0.9rem;
            color: #1e3a8a;
        }
        .account-item {
            margin-bottom: 0.25rem;
        }
        button {
            padding: 0.4rem 0.85rem;
            background: #2563eb;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: 600;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background: #1e40af;
        }
        button.status-btn {
            background: #d97706;
        }
        button.status-btn:hover {
            background: #b45309;
        }
        button[disabled], .btn-disabled {
            background-color: #9ca3af; /* Gray color */
            cursor: not-allowed;
            color: #f3f4f6; /* lighter text */
            /* Prevent pointer events */
            pointer-events: none;
            opacity: 0.7;
        }
        /* Responsive */
        @media (max-width: 768px) {
            table, thead, tbody, th, td, tr { display: block; }
            thead tr { position: absolute; top: -9999px; left: -9999px; }
            tr { background: white; margin-bottom: 1rem; border-radius: 8px; padding: 1rem; box-shadow: 0 2px 10px #cbd5e1; }
            td {
                border: none;
                position: relative;
                padding-left: 50%;
                text-align: right;
                font-size: 0.9rem;
            }
            td::before {
                content: attr(data-label);
                position: absolute;
                left: 1rem;
                width: 45%;
                padding-left: 0;
                font-weight: 700;
                text-align: left;
                color: #475569;
            }
            button { width: 100%; }
            button[disabled], .btn-disabled {
                background-color: #9ca3af; /* Gray color */
                cursor: not-allowed;
                color: #f3f4f6; /* lighter text */
                /* Prevent pointer events */
                pointer-events: none;
                opacity: 0.7;
            }

        }
    </style>

    <!-- Link to external JS file -->
    <script src="${pageContext.request.contextPath}/js/userManagement.js" defer></script>

</head>
<body>

<h1>User Management</h1>

<div id="loading" style="text-align:center; font-weight:600; color:#2563eb;">Loading user data...</div>

<table id="usersTable" role="table" aria-label="User Details Table" style="display:none;">
    <thead>
    <tr>
        <th>Name</th>
        <th>Contact</th>
        <th>Email</th>
        <th>User Type</th>
        <th>Status</th>
        <th>Account Type</th>
        <th>Verification State</th>
        <th>Accounts</th>
        <th>Change User Status</th>
        <th>Create New Account</th>
    </tr>
    </thead>
    <tbody id="usersTableBody">
    <!-- Rows inserted by JS -->
    </tbody>
</table>

</body>
</html>
