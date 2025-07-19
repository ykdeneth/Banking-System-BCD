// profile.js

// Escaping helper to avoid injection
function escapeHtml(text) {
    if (typeof text !== 'string') return text;
    return text.replace(/[&<>"'`=\/]/g, function (s) {
        return {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#39;',
            '`': '&#96;',
            '=': '&#61;',
            '/': '&#47;'
        }[s];
    });
}

// Populate profile inputs and accounts section from user data
function populateProfile(user) {
    document.getElementById('name').value = escapeHtml(user.name || '');
    document.getElementById('contact').value = escapeHtml(user.contact || '');
    document.getElementById('email').value = escapeHtml(user.email || '');
    document.getElementById('userType').value = escapeHtml(user.userType || '');
    document.getElementById('status').value = escapeHtml(user.status || '');
    document.getElementById('accType').value = escapeHtml(user.accType || '');
    document.getElementById('verifyState').value = escapeHtml(user.verifyState || '');

    const accountsContainer = document.getElementById('accountsContainer');
    accountsContainer.innerHTML = '';

    if (user.accounts && user.accounts.length > 0) {
        user.accounts.forEach(account => {
            const div = document.createElement('div');
            div.className = 'account-item';
            div.innerHTML = `
        <div class="account-label">Account Number: ${escapeHtml(account.accountNo)}</div>
        <div>Balance: LKR ${account.balance.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}</div>
      `;
            accountsContainer.appendChild(div);
        });
    } else {
        accountsContainer.textContent = 'No accounts found.';
    }
}


// Fetch user data and populate the form
async function fetchUserProfile() {
    const email = window.loggedUserEmail || "";

    const loadingMsg = document.getElementById('loadingMessage');
    const form = document.getElementById('profileForm');

    if (!email) {
        loadingMsg.textContent = 'Email parameter missing in URL.';
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/bank-web/profile?email=${encodeURIComponent(email)}`, {
            credentials: 'same-origin'
        });

        if (!response.ok) throw new Error(`HTTP error ${response.status}`);

        const data = await response.json();

        if (!data.success || !data.content || data.content.length === 0) {
            loadingMsg.textContent = "User profile not found.";
            return;
        }

        const user = data.content[0];

        populateProfile(user);

        loadingMsg.style.display = 'none';
        form.style.display = 'block';

    } catch (error) {
        loadingMsg.textContent = 'Error loading user profile: ' + error.message;
    }
}

window.addEventListener('DOMContentLoaded', fetchUserProfile);
