<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Schedule Transaction - SL National Bank</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, Helvetica, sans-serif;
            background: url('${pageContext.request.contextPath}/assets/bg.jpg') no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh;
            color: #1e40af;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        body::before {
            content: "";
            position: fixed;
            top: 0; left: 0;
            width: 100vw; height: 100vh;
            background: rgba(0, 0, 50, 0.6);
            z-index: -1;
        }
        .container {
            margin: 2rem auto;
            padding: 2.5rem 2.2rem;
            background: rgba(255,255,255,0.93);
            border-radius: 20px;
            max-width: 460px;
            width: 100%;
            box-shadow: 0 8px 28px rgba(30, 64, 175, 0.13);
            animation: slideUpFadeIn 0.9s ease;
        }
        @keyframes slideUpFadeIn {
            0% { opacity: 0; transform: translateY(35px);}
            100% { opacity: 1; transform: translateY(0); }
        }
        h1 {
            font-weight: 900;
            font-size: 2rem;
            color: #3b82f6;
            margin-bottom: 1.4rem;
            text-align: center;
            letter-spacing: .5px;
            user-select: none;
        }
        form label {
            font-weight: 700;
            color: #2563eb;
            display: block;
            margin-bottom: 0.35rem;
            margin-top: 1.1rem;
        }
        input, textarea, select {
            width: 100%;
            padding: 0.45rem 0.7rem;
            font-size: 1rem;
            margin-bottom: 0.1rem;
            border: 1.5px solid #b6c3d7;
            border-radius: 7px;
            background: #f1f5fd;
            color: #222f61;
            font-family: inherit;
            transition: border-color 0.2s, background 0.2s;
        }
        input:focus, textarea:focus, select:focus {
            border-color: #3b82f6;
            background: #e0e7ff;
            outline: none;
        }
        .form-row {
            display: flex;
            gap: 1rem;
        }
        .form-row > * {
            flex: 1;
        }
        button[type="submit"] {
            margin-top: 1.7rem;
            width: 100%;
            background: linear-gradient(90deg, #2563eb 0%, #6b21a8 100%);
            color: white;
            font-weight: 700;
            font-size: 1.2rem;
            padding: 0.75rem 1.2rem;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            box-shadow: 0 6px 16px rgba(59,130,246,0.23);
            transition: background 0.23s, transform 0.18s;
        }
        button[type="submit"]:hover, button[type="submit"]:focus {
            background: linear-gradient(90deg, #6b21a8 0%, #2563eb 100%);
            transform: scale(1.04) translateY(-3px);
        }
        .msg {
            font-size: 1rem;
            margin: 1rem 0 0 0;
            text-align: center;
            font-weight: 700;
        }
        .msg.success { color: #22c55e; }
        .msg.error { color: #ef4444; }
        @media (max-width: 500px) {
            .container { padding: 1.2rem 0.5rem; }
            h1 { font-size: 1.25rem; }
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/scheduletransaction.js" defer></script>
</head>
<body>
<div class="container">
    <h1>Schedule Transaction</h1>
    <form id="scheduleForm" autocomplete="off">
        <label for="sourceAccountNo">Source Account No</label>
        <input type="text" required id="sourceAccountNo" name="sourceAccountNo" maxlength="30" pattern="[0-9]+" placeholder="Enter your account number" />

        <label for="destinationAccountNo">Destination Account No</label>
        <input type="text" required id="destinationAccountNo" name="destinationAccountNo" maxlength="30" pattern="[0-9]+" placeholder="Enter account number" />

        <label for="amount">Amount (LKR)</label>
        <input type="number" required step="0.01" min="1" id="amount" name="amount" placeholder="Enter amount" />

        <label for="reason">Reason</label>
        <textarea id="reason" name="reason" required maxlength="100" rows="2" placeholder="Reason for transfer"></textarea>

        <div class="form-row">
            <div>
                <label for="year">Year</label>
                <input type="number" required id="year" name="year" min="2025" max="2100" />
            </div>
            <div>
                <label for="month">Month</label>
                <select id="month" name="month" required>
                    <option value="">--</option>
                    <option value="1">Jan</option>
                    <option value="2">Feb</option>
                    <option value="3">Mar</option>
                    <option value="4">Apr</option>
                    <option value="5">May</option>
                    <option value="6">Jun</option>
                    <option value="7">Jul</option>
                    <option value="8">Aug</option>
                    <option value="9">Sep</option>
                    <option value="10">Oct</option>
                    <option value="11">Nov</option>
                    <option value="12">Dec</option>
                </select>
            </div>
            <div>
                <label for="day">Day</label>
                <input type="number" required id="day" name="day" min="1" max="31" />
            </div>
        </div>

        <button type="submit">Schedule Transfer</button>
    </form>
    <div id="msgBox" class="msg" style="display:none"></div>
</div>
</body>
</html>
