package com.ydm.j2ee.core.service;

import com.ydm.j2ee.core.model.Loan;

public interface LoanService {
    Loan requestLoan(String accountNo, double amount, String email);
}
