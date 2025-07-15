package com.ydm.j2ee.core.service;

import com.ydm.j2ee.core.model.Loan;
import com.ydm.j2ee.core.model.LoanStatus;

public interface LoanApprovalService {
        Loan updateLoanStatus(Integer loanId, LoanStatus newStatus);
}
