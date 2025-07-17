<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>SL National Bank - Register</title>
    <style>
        /* Global reset and font */
        * {
            box-sizing: border-box;
        }
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, Helvetica, sans-serif; /* Sans-serif font */
            background-image: url('${pageContext.request.contextPath}/assets/bg.jpg');  /* Replace with your image path or URL */
            background-size: cover;          /* Make sure the image covers the entire background */
            background-position: center;     /* Center the image */
            background-repeat: no-repeat;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #fff;
        }

        /* Container with fade-in animation */
        .register-container {
            background: rgba(255, 255, 255, 0.1);
            padding: 2.5rem 3rem;
            width: 100%;
            max-width: 420px;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            animation: fadeInSlideUp 1s ease forwards;
        }

        @keyframes fadeInSlideUp {
            0% {
                opacity: 0;
                transform: translateY(20px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }

        h2 {
            margin-top: 0;
            font-weight: 700;
            font-size: 1.9rem;
            margin-bottom: 1.5rem;
            text-align: center;
        }

        /* Form and inputs styling */
        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: 600;
            margin-bottom: 0.3rem;
            margin-top: 1rem;
            user-select: none;
        }

        input[type="text"],
        input[type="email"],
        input[type="tel"],
        input[type="password"],
        select {
            padding: 0.65rem 1rem;
            font-size: 1rem;
            border-radius: 8px;
            border: none;
            outline: none;
            transition: box-shadow 0.3s ease, transform 0.3s ease;
            font-family: Arial, Helvetica, sans-serif;
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="tel"]:focus,
        input[type="password"]:focus,
        select:focus {
            box-shadow: 0 0 10px 2px #4f46e5;
            transform: scale(1.03);
        }

        /* Submit button */
        button[type="submit"] {
            margin-top: 2rem;
            background: linear-gradient(90deg, #4f46e5, #a78bfa);
            border: none;
            color: white;
            font-size: 1.15rem;
            font-weight: 700;
            padding: 0.8rem 0;
            border-radius: 32px;
            cursor: pointer;
            transition: background 0.35s ease, transform 0.25s ease;
            user-select: none;
            box-shadow: 0 6px 12px rgba(79, 70, 229, 0.5);
        }

        button[type="submit"]:hover {
            background: linear-gradient(90deg, #a78bfa, #4f46e5);
            transform: scale(1.05);
            box-shadow: 0 10px 20px rgba(79, 70, 229, 0.75);
        }

        /* Responsive adjustment */
        @media (max-width: 480px) {
            .register-container {
                padding: 2rem;
                margin: 0 15px;
            }
            h2 {
                font-size: 1.6rem;
            }
            button[type="submit"] {
                font-size: 1rem;
                padding: 0.7rem 0;
            }
        }
    </style>
</head>
<body>

<div class="register-container">
    <h2>Create Your Account</h2>

    <form action="${pageContext.request.contextPath}/register" method="post" autocomplete="off" novalidate>
        <label for="name">Full Name</label>
        <input type="text" id="name" name="name" required placeholder="Enter your full name" />

        <label for="email">Email Address</label>
        <input type="email" id="email" name="email" required placeholder="Enter your email address" />

        <label for="contact">Contact Number</label>
        <input type="tel" id="contact" name="contact" required pattern="[0-9+ ()-]{7,20}" title="Enter a valid contact number" placeholder="Enter phone number" />

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required minlength="6" placeholder="Enter your password" />

        <label for="userType">User Type</label>
        <select id="userType" name="userType" required>
            <option value="" disabled selected>Select user type</option>
            <option value="admin">Admin</option>
            <option value="user">User</option>
            <option value="officer">Officer</option>
        </select>

        <label for="accType">Account Type</label>
        <select id="accType" name="accType" required>
            <option value="" disabled selected>Select account type</option>
            <option value="saving">Saving Account</option>
            <option value="current">Current Account</option>
        </select>

        <button type="submit">Register</button>
    </form>
</div>

</body>
</html>
