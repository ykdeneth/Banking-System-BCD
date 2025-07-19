<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Daily Transactions - SL National Bank</title>

    <style>
        /* Reset and base font */
        body, html {
            margin: 0; padding: 0;
            height: 100%;
            font-family: Arial, Helvetica, sans-serif;
            background: url('${pageContext.request.contextPath}/assets/bg.jpg') no-repeat center center fixed;
            background-size: cover;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding: 2rem 1rem;
        }
        /* Overlay for better text readability */
        body::before {
            content: "";
            position: fixed;
            top: 0; left: 0;
            width: 100%; height: 100%;
            background: rgba(0,0,50,0.6);
            z-index: -1;
        }

        main {
            max-width: 1100px;
            width: 100%;
            background: rgba(0,0,80,0.75);
            border-radius: 20px;
            padding: 2rem;
            box-shadow: 0 12px 32px rgba(0,0,80,0.9);
            color: #f0f7ff;
            user-select: none;
        }

        h1 {
            text-align: center;
            font-weight: 900;
            margin-bottom: 1.5rem;
            color: #93c5fd; /* lighter blue */
            text-shadow: 0 1px 4px rgba(0,0,0,0.6);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
            font-size: 0.95rem;
            min-width: 720px;
        }

        thead {
            background-color: #2563eb;
            color: white;
            font-weight: 700;
        }

        th, td {
            padding: 0.85rem 1rem;
            border-bottom: 1px solid rgba(255,255,255,0.2);
        }

        tbody tr:hover {
            background-color: rgba(59,130,246, 0.2);
            transition: background-color 0.3s ease;
        }

        /* Responsive adjustments */
        @media (max-width: 760px) {
            main {
                padding: 1rem;
            }
            table {
                min-width: unset;
                font-size: 0.85rem;
            }
            thead {
                display: none; /* Hide headers on small screens */
            }
            tbody tr {
                display: block;
                margin-bottom: 1rem;
                background: rgba(59,130,246,0.15);
                border-radius: 12px;
                padding: 1rem;
            }
            tbody td {
                display: flex;
                justify-content: space-between;
                padding: 0.5rem 0;
                border-bottom: none;
                position: relative;
            }
            tbody td::before {
                content: attr(data-label);
                font-weight: 700;
                color: #1e3a8a;
                flex: 1;
                max-width: 45%;
                padding-right: 0.5rem;
            }
        }
    </style>

    <script src="${pageContext.request.contextPath}/js/dailyTransactions.js" defer></script>

</head>
<body>
<main role="main" aria-label="Daily Transactions List">
    <h1>Today's Transactions</h1>

    <div id="loading" style="text-align:center; font-weight:600; color:#93c5fd;">Loading transactions...</div>

    <table id="transactionsTable" aria-describedby="loading" style="display:none;" role="table" aria-label="List of daily transactions">
        <thead>
        <tr>
            <th>ID</th>
            <th>From Name</th>
            <th>To Name</th>
            <th>Amount (LKR)</th>
            <th>Reason</th>
            <th>Transaction Date</th>
            <th>Type</th>
            <th>From Account No</th>
            <th>To Account No</th>
        </tr>
        </thead>
        <tbody id="transactionsTableBody">
        <!-- JS injects transaction rows here -->
        </tbody>
    </table>

</main>
</body>
</html>
