<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>On-Time Transfer - SL National Bank</title>
    <style>
        body, html {
            min-height: 100vh;
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            background: url('${pageContext.request.contextPath}/assets/bg.jpg') no-repeat center center fixed;
            background-size: cover;
            color: #222f61;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        body::before {
            content: "";
            position: fixed;
            top: 0; left: 0; right: 0; bottom: 0;
            background: rgba(0, 0, 50, 0.60);
            z-index: -1;
        }
        .container {
            background: rgba(255,255,255,0.92);
            border-radius: 20px;
            margin: 2rem auto;
            padding: 2.3rem 2rem;
            max-width: 430px;
            width: 100%;
            box-shadow: 0 8px 30px rgba(30,64,175,0.11);
            animation: fadeinmoveup 0.8s ease;
        }
        @keyframes fadeinmoveup {
            from { opacity: 0; transform: translateY(35px);}
            to { opacity: 1; transform: translateY(0);}
        }
        h1 {
            text-align: center;
            margin-bottom: 1.3rem;
            color: #3b82f6;
            font-size: 2rem;
            font-weight: bold;
            user-select: none;
        }
        form label {
            font-weight: 700;
            color: #2563eb;
            margin-bottom: 0.28rem;
            display: block;
            margin-top: 1.08rem;
            user-select: none;
        }
        input, select, textarea {
            width: 100%;
            padding: 0.5rem 0.75rem;
            font-size: 1rem;
            background: #f2f6fc;
            border: 1.5px solid #b6c3d7;
            border-radius: 6px;
            color: #202640;
            font-family: inherit;
            margin-bottom: 0.15rem;
            transition: border-color 0.23s, background 0.23s;
        }
        input:focus, select:focus, textarea:focus {
            border-color: #3b82f6;
            background: #e0e7ff;
            outline: none;
        }
        button[type="submit"] {
            margin-top: 1.6rem;
            width: 100%;
            background: linear-gradient(90deg, #2563eb, #6b21a8);
            color: #fff;
            font-weight: 700;
            font-size: 1.14rem;
            padding: 0.7rem 0;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            box-shadow: 0 7px 17px rgba(59,130,246,0.24);
            transition: background 0.19s, transform 0.19s;
        }
        button[type="submit"]:hover, button[type="submit"]:focus {
            background: linear-gradient(90deg, #6b21a8, #2563eb);
            transform: scale(1.045) translateY(-4px);
        }
        .msg {
            font-size: 1rem;
            margin-top: 1rem;
            text-align: center;
            font-weight: 700;
            display: none;
        }
        .msg.success { color: #22c55e;}
        .msg.error { color: #ef4444;}
        @media (max-width: 480px) {
            .container { padding: 1rem 0.2rem; }
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/transfer.js" defer></script>
</head>
<body>
<div class="container">
    <h1>On-Time Transfer</h1>
    <form id="transferForm" autocomplete="off">
        <label for="sourceAccount">Source Account No</label>
        <input type="text" required id="sourceAccount" name="sourceAccount" pattern="[0-9]{6,}" maxlength="30" placeholder="Your account number" />

        <label for="destinationAccountNo">Destination Account No</label>
        <input type="text" required id="destinationAccountNo" name="destinationAccountNo" pattern="[0-9]{6,}" maxlength="30" placeholder="Recipient account number" />

        <label for="amount">Amount (LKR)</label>
        <input type="number" min="1" step="0.01" required id="amount" name="amount" placeholder="Enter amount" />

        <label for="reason">Reason</label>
        <textarea required id="reason" name="reason" maxlength="100" rows="2" placeholder="Reason for transfer"></textarea>

        <label for="type">Type</label>
        <select id="type" name="type" required>
            <option value="">-- Select Type --</option>
            <option value="ON_TIME">On Time</option>
            <option value="SCHEDULED">Scheduled</option>
            <option value="INTERESTED_RATE">Interested Rate</option>
        </select>

        <button type="submit">Transfer</button>
    </form>
    <div id="msgBox" class="msg"></div>
</div>
</body>
</html>
