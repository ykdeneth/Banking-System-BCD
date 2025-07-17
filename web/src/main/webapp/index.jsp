<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>SL National Bank - Welcome</title>
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

        .content {
            position: relative;
            z-index: 2;
            max-width: 500px;
            background: rgba(0,0,50,0.7);
            padding: 2.3rem 2.5rem 2.8rem;
            border-radius: 15px;
            box-shadow: 0 12px 24px rgba(0,0,0,0.6);
            animation: fadeSlideIn 1.5s ease forwards;
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

        h1 {
            font-size: 2.8rem;
            margin-bottom: 0.8rem;
            letter-spacing: 1.2px;
            font-weight: 900;
            text-shadow: 1px 1px 6px rgba(0,0,0,0.7);
        }

        p.description {
            font-size: 1.2rem;
            line-height: 1.5;
            margin-bottom: 2rem;
            font-weight: 400;
            text-shadow: 1px 1px 3px rgba(0,0,0,0.6);
        }

        .button-group {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 1.2rem;
        }

        .btn {
            background: linear-gradient(90deg, #1e40af, #3b82f6);
            border: none;
            color: white;
            font-weight: 700;
            font-size: 1.1rem;
            padding: 0.85rem 2.5rem;
            border-radius: 32px;
            cursor: pointer;
            text-decoration: none;
            transition: background 0.4s ease, transform 0.25s ease, box-shadow 0.25s ease;
            box-shadow: 0 5px 14px rgba(59,130,246,0.5);
            user-select: none;
            display: inline-block;
            min-width: 140px;
        }

        .btn:hover, .btn:focus {
            background: linear-gradient(90deg, #3b82f6, #1e40af);
            transform: translateY(-3px) scale(1.05);
            box-shadow: 0 15px 25px rgba(59,130,246,0.75);
            outline: none;
        }

        /* Responsive text and button adjustments */
        @media (max-width: 600px) {
            h1 {
                font-size: 2rem;
            }
            p.description {
                font-size: 1rem;
                margin-bottom: 1.5rem;
            }
            .btn {
                min-width: 100%;
                padding: 0.9rem 0;
            }
            .button-group {
                flex-direction: column;
                gap: 1rem;
            }
        }
    </style>
</head>
<body>
<div class="overlay"></div>
<main class="content" role="main" aria-label="SL National Bank Welcome Section">
    <h1>SL National Bank</h1>
    <p class="description">
        Proudly serving Sri Lanka with secure, innovative, and customer-centric banking solutions.
        Join us today and experience personalized financial services trusted nationwide.
    </p>

    <div class="button-group" role="group" aria-label="Authentication Navigation">
        <a href="${pageContext.request.contextPath}/login.jsp" class="btn" role="button" tabindex="0">Login</a>
        <a href="${pageContext.request.contextPath}/register.jsp" class="btn" role="button" tabindex="0">Register</a>
    </div>
</main>
</body>
</html>
