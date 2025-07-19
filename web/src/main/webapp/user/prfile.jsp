<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>User Profile - SL National Bank</title>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: #f3f7ff;
            margin: 2rem;
            color: #1e40af;
        }
        h1 {
            text-align: center;
            margin-bottom: 1.5rem;
            user-select: none;
        }
        #profileContainer {
            max-width: 700px;
            background: white;
            padding: 2rem;
            border-radius: 12px;
            box-shadow: 0 8px 24px rgba(30, 64, 175, 0.15);
            margin: 0 auto;
        }
        .field-group {
            margin-bottom: 1.2rem;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
        }
        label {
            width: 150px;
            font-weight: 700;
            color: #2563eb;
            user-select: none;
        }
        input {
            flex: 1;
            padding: 0.5rem 0.75rem;
            font-size: 1rem;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            background-color: #f9fafb;
            color: #334155;
            user-select: text;
        }
        input[readonly] {
            background-color: #e0e7ff;
            cursor: default;
        }
        .accounts-section {
            margin-top: 2rem;
        }
        .account-item {
            padding: 1rem;
            background-color: #e0e7ff;
            border-radius: 10px;
            margin-bottom: 1rem;
            box-shadow: 0 2px 7px rgba(59, 130, 246, 0.20);
        }
        .account-label {
            font-weight: 700;
            color: #1e3a8a;
            margin-bottom: 0.5rem;
            user-select: none;
        }
    </style>
    <script>
        var loggedUserEmail = "${fn:escapeXml(sessionScope.loggeduser)}";

        // For debugging, print it:
        console.log("Logged user email:", loggedUserEmail);
    </script>
    <!-- Link external JS file -->
    <script src="${pageContext.request.contextPath}/js/profile.js" defer></script>

</head>
<body>

<h1>User Profile</h1>

<div id="profileContainer" aria-live="polite">
    <p id="loadingMessage" style="font-weight:600; text-align:center; color:#2563eb;">
        Loading user profile...
    </p>

    <!-- Profile data will be injected here -->
    <form id="profileForm" style="display:none;" autocomplete="off" aria-label="User profile details">
        <div class="field-group">
            <label for="name">Name:</label>
            <input id="name" name="name" type="text" readonly />
        </div>
        <div class="field-group">
            <label for="contact">Contact:</label>
            <input id="contact" name="contact" type="text" readonly />
        </div>
        <div class="field-group">
            <label for="email">Email:</label>
            <input id="email" name="email" type="email" readonly />
        </div>
        <div class="field-group">
            <label for="userType">User Type:</label>
            <input id="userType" name="userType" type="text" readonly />
        </div>
        <div class="field-group">
            <label for="status">Status:</label>
            <input id="status" name="status" type="text" readonly />
        </div>
        <div class="field-group">
            <label for="accType">Account Type:</label>
            <input id="accType" name="accType" type="text" readonly />
        </div>
        <div class="field-group">
            <label for="verifyState">Verification State:</label>
            <input id="verifyState" name="verifyState" type="text" readonly />
        </div>

        <div class="accounts-section" aria-label="User accounts">
            <h2 style="color:#2563eb; user-select:none;">Accounts</h2>
            <div id="accountsContainer">
                <!-- Account info injected here -->
            </div>
        </div>
    </form>

</div>

</body>
</html>
