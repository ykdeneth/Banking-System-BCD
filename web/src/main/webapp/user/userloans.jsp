<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Your Loan Details - SL National Bank</title>
    <style>
        body, html {
            font-family: Arial, Helvetica, sans-serif;
            background: url('${pageContext.request.contextPath}/assets/bg.jpg') no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh;
            margin: 0; padding: 0;
            color: #1e40af;
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
            background: rgba(255,255,255,0.97);
            border-radius: 18px;
            margin: 2rem auto;
            padding: 2.2rem 1.7rem 2rem 1.7rem;
            max-width: 900px;
            width: 98%;
            box-shadow: 0 10px 30px rgba(30,64,175,0.14);
            animation: fadeinmoveup 0.89s ease;
        }
        @keyframes fadeinmoveup {
            from { opacity: 0; transform: translateY(30px);}
            to { opacity: 1; transform: translateY(0);}
        }
        h1 {
            font-weight: 900;
            font-size: 2rem;
            color: #3b82f6;
            text-align: center;
            margin-bottom: 1.2rem;
            letter-spacing: 1.2px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
        }
        thead {
            background: #2563eb;
            color: #fff;
        }
        th, td {
            padding: 0.78rem 1rem;
            font-size: 0.98rem;
            border-bottom: 1px solid #e3e8f9;
        }
        tr:nth-child(even) { background: #f4f7fd; }
        tr:hover { background-color: #e0e7ff; }
        .msg {
            font-size: 1.07rem;
            text-align: center;
            color: #ef4444;
            margin: 1rem 0 1rem 0;
            font-weight: 700;
            display: none;
        }
        @media (max-width: 750px) {
            .container { padding: 1rem 0.2rem;}
            th, td { font-size: 0.93rem; }
        }
        @media (max-width: 520px) {
            .container { padding: 0.5rem 0;}
            h1 { font-size: 1.18rem; }
            table, th, td { font-size: 0.81rem; }
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/userloans.js" defer></script>
</head>
<body>
<div class="container">
    <h1>Your Loans</h1>
    <div id="msgBox" class="msg"></div>
    <table id="loansTable" style="display:none;" aria-label="User Loan Table">
        <thead>
        <tr>
            <th>Loan ID</th>
            <th>Account No</th>
            <th>Amount (LKR)</th>
            <th>Status</th>
            <th>Requested Date</th>
        </tr>
        </thead>
        <tbody id="loansBody">
        <!-- JavaScript will insert rows -->
        </tbody>
    </table>
</div>
</body>
</html>
