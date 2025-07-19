// applyloan.js

document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById('applyLoanForm');
    const msgBox = document.getElementById('msgBox');

    form.addEventListener('submit', async function (e) {
        e.preventDefault();
        msgBox.style.display = "none";

        const data = {
            accountNo: form.accountNo.value.trim(),
            amount: form.amount.value
        };

        if (!data.accountNo || !data.amount) {
            msgBox.textContent = "Please complete all fields.";
            msgBox.className = "msg error";
            msgBox.style.display = "block";
            return;
        }

        try {
            const params = new URLSearchParams(data).toString();
            const response = await fetch('http://localhost:8080/bank-web/applyLoan?' + params, {
                method: 'GET',
                credentials: 'same-origin'
            });
            const result = await response.json();

            if (response.ok && result.success) {
                msgBox.textContent = "Loan applied successfully! Loan ID: " + (result.content && result.content.id ? result.content.id : "");
                msgBox.className = "msg success";
                form.reset();
            } else {
                msgBox.textContent = "Loan request failed: " + (result.content || "Unknown error.");
                msgBox.className = "msg error";
            }
        } catch (err) {
            msgBox.textContent = "Network or server error.";
            msgBox.className = "msg error";
        }
        msgBox.style.display = "block";
    });
});
