// transactionhistory.js

// Helper for safe HTML rendering
function escapeHtml(text) {
    if (typeof text !== 'string') return text;
    return text.replace(/[&<>"'`=\/]/g, function (s) {
        return { '&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;', '`':'&#96;', '=':'&#61;', '/':'&#47;' }[s];
    });
}

async function fetchTransactionHistory() {
    const msgBox = document.getElementById('msgBox');
    const table = document.getElementById('historyTable');
    const tbody = document.getElementById('historyBody');

    msgBox.style.display = "none";
    tbody.innerHTML = "";
    table.style.display = "none";

    // Changed: use session variable not URL or form
    const email = window.loggedUserEmail || "";
    if (!email) {
        msgBox.textContent = "You are not logged in. Please log in first.";
        msgBox.style.display = "block";
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/bank-web/transaction_history', {
            method: 'POST',
            credentials: 'same-origin',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ email: email }).toString()
        });

        if (!response.ok) throw new Error(`HTTP error ${response.status}`);

        const data = await response.json();

        if (!data.success || !data.content || data.content.length === 0) {
            msgBox.textContent = "No transactions found.";
            msgBox.style.display = "block";
            return;
        }

        // Render rows
        data.content.forEach(tx => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
        <td>${escapeHtml(String(tx.id))}</td>
        <td>${escapeHtml(tx.fromAccountNo)}</td>
        <td>${escapeHtml(tx.toAccountNo)}</td>
        <td>${Number(tx.amount).toLocaleString(undefined, {minimumFractionDigits: 2})}</td>
        <td>${escapeHtml(tx.reason || "")}</td>
        <td>${escapeHtml(tx.transactionDate)}</td>
        <td>${escapeHtml(tx.transactionType)}</td>
      `;
            tbody.appendChild(tr);
        });

        table.style.display = "table";
        msgBox.style.display = "none";
    } catch (error) {
        msgBox.textContent = "Error loading transactions: " + error.message;
        msgBox.style.display = "block";
    }
}

window.addEventListener('DOMContentLoaded', fetchTransactionHistory);
