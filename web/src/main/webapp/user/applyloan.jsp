<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Apply for Loan - SL National Bank</title>
    <style>
        body, html {
            background: url('${pageContext.request.contextPath}/assets/bg.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0; padding: 0;
            min-height: 100vh;
            font-family: Arial, Helvetica, sans-serif;
            color: #18355e;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
        }
        body::before {
            content: "";
            position: fixed;
            inset: 0;
            background: rgba(0, 0, 50, 0.66);
            z-index: -1;
        }
        .box {
            background: rgba(255,255,255,0.97);
            border-radius: 20px;
            margin-top: 2.7rem;
            margin-bottom: 2rem;
            padding: 2.2rem 1.1rem 2rem 1.1rem;
            min-width: 340px;
            width: 90%;
            max-width: 420px;
            box-shadow: 0 7px 22px rgba(30,64,175,0.14);
            animation: fadeinmoveup 0.89s ease;
        }
        @keyframes fadeinmoveup {
            from { opacity: 0; transform: translateY(30px);}
            to { opacity: 1; transform: translateY(0);}
        }
        h2 {
            color: #3b82f6;
            font-size: 1.6rem;
            font-weight: bold;
            text-align: center;
            margin: 0 0 2rem 0;
            letter-spacing: 0.7px;
            user-select: none;
        }
        .input-row {
            display: flex;
            align-items: center;
            margin-bottom: 1.25rem;
        }
        label {
            flex: 0 0 110px;
            font-weight: 700;
            color: #2563eb;
            margin-right: 0.7rem;
            user-select: none;
        }
        input[type="text"], input[type="number"] {
            flex: 1;
            padding: 0.5rem 0.7rem;
            font-size: 1rem;
            border: 1.5px solid #b6c3d7;
            border-radius: 6px;
            background: #f2f6fc;
            color: #153e75;
            font-family: inherit;
            transition: border-color 0.19s, background 0.19s;
        }
        input[type="text"]:focus, input[type="number"]:focus {
            border-color: #3b82f6;
            background: #e0e7ff;
            outline: none;
        }
        .actions {
            display: flex;
            flex-direction: column;
            gap: 1rem;
            margin-top: 1.8rem;
        }
        button, .btn-link {
            width: 100%;
            background: linear-gradient(90deg, #2563eb, #6b21a8);
            color: #fff;
            font-weight: 700;
            font-size: 1.13rem;
            padding: 0.7rem;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            margin-top: 0.1rem;
            text-align: center;
            text-decoration: none;
            display: block;
            box-shadow: 0 4px 18px rgba(59,130,246,0.24);
            transition: background 0.18s, transform 0.15s;
        }
        button:hover, button:focus, .btn-link:hover, .btn-link:focus {
            background: linear-gradient(90deg, #6b21a8, #2563eb);
            transform: scale(1.045) translateY(-3px);
        }
        .msg {
            font-size: 1.02rem;
            text-align: center;
            color: #2c687f;
            margin-top: 1rem;
            font-weight: 700;
            display: none;
        }
        .msg.success { color: #22c55e;}
        .msg.error { color: #ef4444;}
        @media (max-width: 480px) {
            .box { padding: 0.8rem 0.2rem;}
            h2 { font-size: 1.13rem;}
            label { flex-basis: 90px;}
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/applyloan.js" defer></script>
</head>
<body>
<div class="box">
    <h2>Apply for a Loan</h2>
    <form id="applyLoanForm" autocomplete="off">
        <div class="input-row">
            <label for="accountNo">Account No</label>
            <input type="text" required id="accountNo" name="accountNo" maxlength="30" pattern="[0-9]{6,}" placeholder="Your account number"/>
        </div>
        <div class="input-row">
            <label for="amount">Amount (LKR)</label>
            <input type="number" required min="1" step="0.01" id="amount" name="amount" placeholder="Loan amount"/>
        </div>
        <div class="actions">
            <button type="submit">Apply</button>
            <a href="${pageContext.request.contextPath}/user/userloans.jsp" class="btn-link" tabindex="0">View My Loans</a>
        </div>
    </form>
    <div id="msgBox" class="msg"></div>
</div>
</body>
</html>
