// Changed: API returns List<LoanResponse_DTO> flat list instead of nested users > accounts > loans
async function fetchLoanUsers() {
    try {
        const response = await fetch('http://localhost:8080/bank-web/loanUsers', { credentials: 'same-origin' });
        if (!response.ok) throw new Error(`HTTP error ${response.status}`);

        const data = await response.json();
        if (!data.success) throw new Error(data.content || "Failed to fetch loan data");

        renderLoanList(data.content); // Changed: Render flat loan list
    } catch (e) {
        alert("Error loading loan data: " + e.message);
    }
}

function escapeHtml(text) {
    if (typeof text !== 'string') return text;
    return text.replace(/[&<>"'`=\/]/g, function (s) {
        return {'&':'&amp;', '<':'&lt;', '>':'&gt;', '"':'&quot;', "'":'&#39;', '`':'&#96;', '=':'&#61;', '/':'&#47;'}[s];
    });
}

// Changed: Render flat list of loans
function renderLoanList(loans) {
    const tbody = document.getElementById('loanTableBody');
    tbody.innerHTML = '';

    loans.forEach(loan => {
        const tr = document.createElement('tr');

        tr.innerHTML = `
          <td>${escapeHtml(loan.userName)}</td>
          <td>${escapeHtml(loan.userEmail)}</td>
          <td>${escapeHtml(loan.accountNo)}</td>
          <td>${loan.loanId}</td>
          <td>${loan.loanAmount.toFixed(2)}</td>
          <td>${escapeHtml(loan.loanStatus)}</td>
          <td>${new Date(loan.requestedDate).toLocaleString()}</td>
          <td>
            ${loan.loanStatus === 'REQUESTED' ? `
              <button class="approve-btn" data-loan-id="${loan.loanId}">Approve</button>
              <button class="reject-btn" data-loan-id="${loan.loanId}">Reject</button>
            ` : '—'}
          </td>
        `;
        tbody.appendChild(tr);
    });

    document.querySelectorAll('.approve-btn').forEach(btn => {
        btn.addEventListener('click', () => updateLoanStatus(btn.dataset.loanId, 'APPROVED'));
    });
    document.querySelectorAll('.reject-btn').forEach(btn => {
        btn.addEventListener('click', () => updateLoanStatus(btn.dataset.loanId, 'REJECTED'));
    });
}

async function updateLoanStatus(loanId, newStatus) {
    if (!confirm(`Are you sure you want to ${newStatus.toLowerCase()} loan #${loanId}?`)) return;

    try {
        const response = await fetch(`http://localhost:8080/bank-web/loanapprove`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({
                loanId: loanId,
                status: newStatus
            }),
            credentials: 'same-origin'
        });
        if (!response.ok) throw new Error(`HTTP ${response.status}`);

        const data = await response.json();
        if (!data.success) throw new Error(data.content || 'Failed to update loan status.');

        alert(`Loan #${loanId} ${newStatus.toLowerCase()} successfully!`);
        fetchLoanUsers();  // Refresh the list
    } catch (err) {
        alert('Error updating loan status: ' + err.message);
    }
}

window.addEventListener('DOMContentLoaded', fetchLoanUsers);
