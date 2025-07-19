// dailyTransactions.js

// Escaper to prevent injection attacks
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

function renderTransactions(transactions) {
    const tbody = document.getElementById('transactionsTableBody');
    tbody.innerHTML = ''; // clear previous rows

    transactions.forEach(tx => {
        const tr = document.createElement('tr');

        tr.innerHTML = `
      <td data-label="ID">${escapeHtml(tx.id.toString())}</td>
      <td data-label="From Name">${escapeHtml(tx.fromName)}</td>
      <td data-label="To Name">${escapeHtml(tx.toName)}</td>
      <td data-label="Amount (LKR)">${tx.amount.toLocaleString(undefined, {minimumFractionDigits:2, maximumFractionDigits:2})}</td>
      <td data-label="Reason">${escapeHtml(tx.reason)}</td>
      <td data-label="Transaction Date">${escapeHtml(tx.transactionDate)}</td>
      <td data-label="Type">${escapeHtml(tx.transactionType)}</td>
      <td data-label="From Account No">${escapeHtml(tx.fromAccountNo)}</td>
      <td data-label="To Account No">${escapeHtml(tx.toAccountNo)}</td>
    `;

        tbody.appendChild(tr);
    });

    document.getElementById('loading').style.display = 'none';
    document.getElementById('transactionsTable').style.display = 'table';
}

async function fetchDailyTransactions() {
    try {
        const response = await fetch('http://localhost:8080/bank-web/dailyTransaction', { credentials: 'same-origin' });

        if (!response.ok) {
            throw new Error(`HTTP error ${response.status}`);
        }

        const data = await response.json();

        if (!data.success) {
            throw new Error(data.content || 'Failed to fetch transactions');
        }

        renderTransactions(data.content);

    } catch (err) {
        const loadingDiv = document.getElementById('loading');
        loadingDiv.textContent = `Error loading transactions: ${err.message}`;
        loadingDiv.style.color = 'red';
    }
}

window.addEventListener('DOMContentLoaded', fetchDailyTransactions);
