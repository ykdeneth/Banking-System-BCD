<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Admin Panel - SL National Bank</title>

    <style>
        /* Reset box sizing */
        *, *::before, *::after {
            box-sizing: border-box;
        }
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            color: #fff;
            background: url('${pageContext.request.contextPath}/assets/bg.jpg');
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        /* Overlay for better contrast */
        body::before {
            content: "";
            position: fixed;
            top: 0; left: 0;
            width: 100%; height: 100%;
            background: rgba(0, 0, 50, 0.6);
            z-index: -1;
        }

        /* Container for the 3 buttons */
        .container {
            display: flex;
            gap: 3rem;
            max-width: 1100px;
            width: 90%;
            justify-content: center;
            flex-wrap: wrap;
        }

        /* Card button style */
        .card-btn {
            background: linear-gradient(135deg, #2563eb 0%, #6b21a8 100%);
            border-radius: 22px;
            padding: 2.5rem 3.5rem;
            width: 300px;
            text-align: center;
            font-weight: 900;
            font-size: 1.5rem;
            letter-spacing: 1.5px;
            cursor: pointer;
            color: #fff;
            text-decoration: none;
            box-shadow: 0 6px 15px rgba(59, 130, 246, 0.7);
            border: 3px solid transparent;
            transition:
                    background 0.5s ease,
                    transform 0.3s cubic-bezier(0.4, 0, 0.2, 1),
                    box-shadow 0.4s ease,
                    border-color 0.4s ease;
            user-select: none;
        }

        .card-btn:hover, .card-btn:focus {
            background: linear-gradient(135deg, #6b21a8 0%, #2563eb 100%);
            box-shadow: 0 12px 30px rgba(59, 130, 246, 0.85);
            transform: translateY(-8px) scale(1.05);
            border-color: #93c5fd;
            outline: none;
        }

        .card-btn:active {
            transform: translateY(-3px) scale(0.98);
            box-shadow: 0 6px 14px rgba(59, 130, 246, 0.6);
        }
        .logout-btn {
            background: linear-gradient(110deg, #ef4444 0%, #ea580c 100%) !important;
            border-color: #fca5a5 !important;
            color: #fff !important;
            transition: background 0.3s, border-color 0.3s;
        }

        .logout-btn:hover, .logout-btn:focus {
            background: linear-gradient(110deg, #ea580c 0%, #ef4444 100%) !important;
            border-color: #fecaca !important;
        }

        .logout-btn:active {
            background: linear-gradient(110deg, #991b1b 0%, #be185d 100%) !important;
        }

        /* Animations for entrance */
        @keyframes slideUpFadeIn {
            0% {
                opacity: 0;
                transform: translateY(40px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .card-btn:nth-child(1) {
            animation: slideUpFadeIn 0.8s ease forwards;
            animation-delay: 0.15s;
        }
        .card-btn:nth-child(2) {
            animation: slideUpFadeIn 0.8s ease forwards;
            animation-delay: 0.35s;
        }
        .card-btn:nth-child(3) {
            animation: slideUpFadeIn 0.8s ease forwards;
            animation-delay: 0.55s;
        }

        /* Responsive */
        @media (max-width: 960px) {
            .container {
                gap: 2rem;
            }
            .card-btn {
                width: 90%;
                padding: 2rem 0;
                font-size: 1.3rem;
            }
        }
    </style>
</head>
<body>

<div class="container" role="main" aria-label="Admin Navigation Panel">
    <a href="${pageContext.request.contextPath}/admin/previewUsers.jsp" class="card-btn" role="button" tabindex="0" aria-label="Navigate to User Management">
        01 - User Management
    </a>
    <a href="${pageContext.request.contextPath}/admin/dailyTransactions.jsp" class="card-btn" role="button" tabindex="0" aria-label="Navigate to Daily Transactions">
        02 - Daily Transactions
    </a>
    <a href="${pageContext.request.contextPath}/admin/loanManagement.jsp" class="card-btn" role="button" tabindex="0" aria-label="Navigate to Loan Management">
        03 - Loan Management
    </a>
    <a href="#" id="logoutBtn" class="card-btn logout-btn" role="button" tabindex="0" aria-label="Log out of your account">
        Logout
    </a>
</div>
<script>
    document.getElementById('logoutBtn').addEventListener('click', function (e) {
        e.preventDefault();
        fetch('${pageContext.request.contextPath}/logout', {
            method: 'GET', // or 'POST' if servlet expects
            credentials: 'same-origin'
        }).then(resp => {
            // Optionally: show a message or redirect
            window.location.href = '${pageContext.request.contextPath}/index.jsp';
        });
    });
</script>
</body>
</html>
