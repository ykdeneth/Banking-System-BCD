<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Transaction History - SL National Bank</title>
    <style>
        body, html {
            font-family: Arial, Helvetica, sans-serif;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            background: url('${pageContext.request.contextPath}/assets/bg.jpg') no-repeat center center fixed;
            background-size: cover;
            color: #222f61;
            display: flex;
            align-items: flex-start;
            justify-content: center;
            padding: 2rem 0;
        }
        body::before {
            content: "";
            position: fixed;
            top: 0; left: 0; right: 0; bottom: 0;
            background: rgba(0, 0, 50, 0.66);
            z-index: -1;
        }
        .container {
            margin: 2rem auto;
            padding: 2.4rem 2rem 2rem 2rem;
            background: rgba(255,255,255,0.95);
            border-radius: 18px;
            max-width: 930px;
            width: 98%;
            box-shadow: 0 10px 32px rgba(30,64,175,0.17);
            animation: slideUpFadeIn 0.9s ease;
        }
        @keyframes slideUpFadeIn {
            from { opacity: 0; transform: translateY(35px);}
            to { opacity: 1; transform: translateY(0);}
        }
        h1 {
            font-weight: 900;
            font-size: 2rem;
            color: #3b82f6;
            text-align: center;
            margin-bottom: 1.2rem;
            letter-spacing: 1.1px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
            margin-top: 0.5rem;
        }
        thead {
            background: #2563eb;
            color: #fff;
        }
        th, td {
            padding: 0.75rem 1rem;
            border-bottom: 1px solid #e3e8f9;
            font-size: 0.99rem;
        }
        tr:nth-child(even) { background: #f4f7fd; }
        tr:hover { background-color: #dbeafe; }
        .msg {
            font-size: 1.03rem;
            text-align: center;
            color: #ef4444;
            margin: 1.2rem 0 0.6rem 0;
            font-weight: 700;
            display: none;
        }
        @media (max-width: 800px) {
            .container { padding: 1.2rem 0.1rem;}
            table, thead, tbody, th, td, tr { font-size: 0.93rem; }
            th, td{ padding: 0.60rem 0.45rem;}
        }
        @media (max-width: 520px) {
            .container { padding: 0.5rem 0; }
            h1 { font-size: 1.2rem; }
            table, th, td { font-size: 0.81rem;}
        }
    </style>
    <script>
        // Pass session email from loggeduser to JavaScript side safely
        var loggedUserEmail = '<c:out value="${sessionScope.loggeduser}" />';
        console.log(loggedUserEmail);
    </script>
    <script src="${pageContext.request.contextPath}/js/transactionhistory.js" defer></script>
</head>
<body>
<div class="container">
    <h1>Your Transaction History</h1>
    <div id="msgBox" class="msg"></div>
    <table id="historyTable" style="display:none;" aria-label="Transaction History Table">
        <thead>
        <tr>
            <th>Transaction ID</th>
            <th>From Account</th>
            <th>To Account</th>
            <th>Amount (LKR)</th>
            <th>Reason</th>
            <th>Date & Time</th>
            <th>Type</th>
        </tr>
        </thead>
        <tbody id="historyBody">
        <!-- JS renders transaction rows here -->
        </tbody>
    </table>
</div>
</body>
</html>
