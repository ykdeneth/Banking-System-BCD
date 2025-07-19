// userloans.js

// Escape helper
function escapeHtml(text) {
    if (typeof text !== 'string') return '';
    return text.replace(/[&<>"'`=\/]/g, function (s) {
        return { '&': '&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;', '`':'&#96;', '=':'&#61;', '/':'&#47;' }[s];
    });
}

async function fetchUserLoans() {
    const msgBox = document.getElementById('msgBox');
    const table = document.getElementById('loansTable');
    const tbody = document.getElementById('loansBody');

    msgBox.style.display = "none";
    tbody.innerHTML = "";
    table.style.display = "none";

    try {
        const response = await fetch('http://localhost:8080/bank-web/userLoanDetails', {
            credentials: 'same-origin'
        });
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        const data = await response.json();
        if (!data.success || !data.content || data.content.length === 0) {
            msgBox.textContent = "No loans found.";
            msgBox.style.display = "block";
            return;
        }
        data.content.forEach(loan => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
        <td>${escapeHtml(String(loan.loanId))}</td>
        <td>${escapeHtml(loan.accountNo)}</td>
        <td>${Number(loan.amount).toLocaleString(undefined, {minimumFractionDigits:2})}</td>
        <td>${escapeHtml(loan.status)}</td>
        <td>${new Date(loan.requestedDate).toLocaleString()}</td>
      `;
            tbody.appendChild(tr);
        });
        table.style.display = "table";
    } catch (err) {
        msgBox.textContent = "Error loading loans: " + err.message;
        msgBox.style.display = "block";
    }
}

window.addEventListener('DOMContentLoaded', fetchUserLoans);
