<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>SL National Bank - Login</title>
    <style>
        /* Reset and base font */
        * {
            box-sizing: border-box;
        }
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            color: #fff;
            background: url('${pageContext.request.contextPath}/assets/bg.jpg') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            padding: 1rem;
            overflow-x: hidden;
        }

        /* Overlay for background darkening */
        .overlay {
            position: fixed;
            top:0; left:0; width:100%; height:100%;
            background: rgba(0,0,50,0.55);
            z-index: 1;
        }

        .login-container {
            position: relative;
            z-index: 2;
            max-width: 400px;
            width: 100%;
            background: rgba(0,0,50,0.75);
            padding: 2.5rem 2.5rem 3rem;
            border-radius: 15px;
            box-shadow: 0 12px 25px rgba(0,0,0,0.6);
            animation: fadeSlideIn 1.2s ease forwards;
            user-select: none;
        }

        @keyframes fadeSlideIn {
            0% {
                opacity: 0;
                transform: translateY(2rem);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }

        h2 {
            font-size: 2.4rem;
            font-weight: 900;
            margin: 0 0 1.5rem 0;
            letter-spacing: 1.2px;
            text-shadow: 1px 1px 6px rgba(0,0,0,0.7);
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 1.15rem;
        }

        label {
            font-weight: 600;
            user-select: none;
            text-align: left;
        }

        input[type="email"],
        input[type="password"] {
            padding: 0.7rem 1rem;
            font-size: 1rem;
            border-radius: 10px;
            border: none;
            outline: none;
            font-family: Arial, Helvetica, sans-serif;
            transition: box-shadow 0.3s ease, transform 0.3s ease;
        }

        input[type="email"]:focus,
        input[type="password"]:focus {
            box-shadow: 0 0 10px 2px #4f46e5;
            transform: scale(1.04);
        }

        button[type="submit"] {
            margin-top: 2rem;
            background: linear-gradient(90deg, #1e40af, #3b82f6);
            border: none;
            color: white;
            font-size: 1.15rem;
            font-weight: 700;
            padding: 0.85rem 0;
            border-radius: 32px;
            cursor: pointer;
            user-select: none;
            transition: background 0.35s ease, transform 0.25s ease, box-shadow 0.25s ease;
            box-shadow: 0 5px 14px rgba(59,130,246,0.5);
        }

        button[type="submit"]:hover,
        button[type="submit"]:focus {
            background: linear-gradient(90deg, #3b82f6, #1e40af);
            transform: translateY(-3px) scale(1.05);
            box-shadow: 0 15px 25px rgba(59,130,246,0.75);
            outline: none;
        }

        /* Error message styling */
        .error-message {
            background: rgba(255, 69, 58, 0.85);
            padding: 0.85rem 1rem;
            border-radius: 12px;
            margin-bottom: 1rem;
            font-weight: 600;
            box-shadow: 0 3px 6px rgba(255, 69, 58, 0.7);
        }

        /* Responsive */
        @media (max-width: 480px) {
            .login-container {
                padding: 2rem 1.8rem 2.5rem;
                margin: 0 1rem;
            }
            h2 {
                font-size: 1.9rem;
            }
            button[type="submit"] {
                font-size: 1rem;
                padding: 0.75rem 0;
            }
        }
    </style>

    <script>
        // Simple client-side validation or message display can be added here if desired.
    </script>
</head>
<body>
<div class="overlay"></div>
<section class="login-container" role="main" aria-label="Login to SL National Bank">
    <h2>Login</h2>

    <%-- Example server-side error display (adjust based on your setup) --%>
    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post" autocomplete="off" novalidate>
        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="Enter your email address" required />

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter your password" required minlength="6" />

        <button type="submit" aria-label="Login to your account">Login</button>
    </form>
</section>
</body>
</html>
