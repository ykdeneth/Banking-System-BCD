// userManagement.js

// Basic HTML escape to prevent injection
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

// Render user data into table
function renderUsers(users) {
    const tbody = document.getElementById('usersTableBody');
    tbody.innerHTML = ''; // clear existing rows

    users.forEach(user => {
        const hasAccounts = user.accounts && user.accounts.length > 0;

        const createAccountButton = hasAccounts
            ? `<button disabled class="btn-disabled" title="User already has account(s)">New Account</button>`
            : `<button onclick="${ user.accounts && user.accounts.length === 0 ? `createNewAccount('${escapeHtml(user.email)}')` : 'return false;' }">New Account</button>`;

        // const tr = document.createElement('tr');

        const tr = document.createElement('tr');

        tr.innerHTML = `
      <td data-label="Name">${escapeHtml(user.name)}</td>
      <td data-label="Contact">${escapeHtml(user.contact)}</td>
      <td data-label="Email">${escapeHtml(user.email)}</td>
      <td data-label="User Type">${escapeHtml(user.userType)}</td>
      <td data-label="Status">${escapeHtml(user.status)}</td>
      <td data-label="Account Type">${escapeHtml(user.accType)}</td>
      <td data-label="Verification State">${escapeHtml(user.verifyState)}</td>
      <td data-label="Accounts">
        ${user.accounts && user.accounts.length > 0
            ? user.accounts.map(acc =>
                `<div class="account-item">#${escapeHtml(acc.accountNo)} - Bal: ${acc.balance.toLocaleString()}</div>`
            ).join('')
            : '<em>No accounts</em>'
        }
      </td>
      <td data-label="Change User Status">
        <button class="status-btn" onclick="changeUserStatus(${user.id}, '${escapeHtml(user.status)}')">
          ${user.status === 'ACTIVE' ? 'Deactivate' : 'Activate'}
        </button>
      </td>
      <td data-label="Create New Account">
<!--        <button onclick="createNewAccount(${user.id})">New Account</button>-->
${createAccountButton}
      </td>
    `;

        tbody.appendChild(tr);
    });

    document.getElementById('loading').style.display = 'none';
    document.getElementById('usersTable').style.display = 'table';
}

// Fetch all users from API and render
async function fetchUsers() {
    try {
        const response = await fetch('http://localhost:8080/bank-web/getallusers', {
            credentials: 'same-origin'
        });

        if (!response.ok) {
            throw new Error('HTTP error ' + response.status);
        }

        const data = await response.json();

        if (!data.success) {
            throw new Error(data.content || 'Failed to fetch users');
        }

        renderUsers(data.content);

    } catch (error) {
        document.getElementById('loading').textContent = 'Error loading users: ' + error.message;
    }
}

// Button handlers (implement backend API calls accordingly)
function changeUserStatus(userId) {
    // alert(`Clicked Change User Status for user id ${userId} (current status: ${currentStatus})`);

}

function createNewAccount(email) {
    alert("create acc");


    fetch('http://localhost:8080/bank-web/createAcc', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({ email: email }).toString()
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("Account created successfully!");
                // You can also refresh/reload users list here if needed
                fetchUsers();
            } else {
                alert("Account creation failed: " + (data.content || 'Unknown error'));
            }
        })
        .catch(error => {
            alert("Network error: " + error.message);
        });
}

// Fetch users when page loads
window.addEventListener('DOMContentLoaded', fetchUsers);
