// scheduletransaction.js

document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('scheduleForm');
    const msgBox = document.getElementById('msgBox');

    form.addEventListener('submit', async function (e) {
        e.preventDefault();
        msgBox.style.display = "none";

        const data = {
            sourceAccountNo: form.sourceAccountNo.value.trim(),
            destinationAccountNo: form.destinationAccountNo.value.trim(),
            amount: form.amount.value,
            reason: form.reason.value.trim(),
            year: form.year.value,
            month: form.month.value,
            day: form.day.value
        };

        // Basic validation for day/month/year
        if (data.month < 1 || data.month > 12) {
            msgBox.textContent = "Invalid month.";
            msgBox.className = "msg error";
            msgBox.style.display = "block";
            return;
        }
        if (data.day < 1 || data.day > 31) {
            msgBox.textContent = "Invalid day of the month.";
            msgBox.className = "msg error";
            msgBox.style.display = "block";
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/bank-web/timerSchedul', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: new URLSearchParams(data).toString(),
                credentials: 'same-origin'
            });

            const text = await response.text();

            if (response.ok && text.includes("success")) {
                msgBox.textContent = "Transfer scheduled successfully!";
                msgBox.className = "msg success";
                form.reset();
            } else {
                msgBox.textContent = text || "Error scheduling transfer.";
                msgBox.className = "msg error";
            }
        } catch (error) {
            msgBox.textContent = "Network or server error.";
            msgBox.className = "msg error";
        }
        msgBox.style.display = "block";
    });

    // Autofill current date for user
    const now = new Date();
    form.year.value = now.getFullYear();
    form.month.value = now.getMonth() + 1; // JS month is 0-based, form expects 1-based
    form.day.value = now.getDate();
});
