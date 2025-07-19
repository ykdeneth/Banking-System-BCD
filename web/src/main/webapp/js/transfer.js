// transfer.js

document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById('transferForm');
    const msgBox = document.getElementById('msgBox');

    form.addEventListener('submit', async function (e) {
        e.preventDefault();
        msgBox.style.display = "none";

        const data = {
            sourceAccount: form.sourceAccount.value.trim(),
            destinationAccountNo: form.destinationAccountNo.value.trim(),
            amount: form.amount.value,
            reason: form.reason.value.trim(),
            type: form.type.value
        };

        if (!data.sourceAccount || !data.destinationAccountNo || !data.amount || !data.reason || !data.type) {
            msgBox.textContent = "Please fill in all fields.";
            msgBox.className = "msg error";
            msgBox.style.display = "block";
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/bank-web/transfer', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: new URLSearchParams(data).toString(),
                credentials: 'same-origin'
            });
            const text = await response.text();

            if (response.ok && !text.includes("not logged in") && !text.includes("Invalid account")) {
                msgBox.textContent = "Transfer successful!";
                msgBox.className = "msg success";
                form.reset();
            } else {
                msgBox.textContent = text || "Error during transfer.";
                msgBox.className = "msg error";
            }
        } catch (error) {
            msgBox.textContent = "Network/server error.";
            msgBox.className = "msg error";
        }
        msgBox.style.display = "block";
    });
});
