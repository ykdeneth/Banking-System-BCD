package com.ydm.j2ee.ejb.bean;

import com.ydm.j2ee.core.model.Loan;
import com.ydm.j2ee.core.model.LoanStatus;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateful
@RolesAllowed("ADMIN")
public class LoanApprovalService implements com.ydm.j2ee.core.service.LoanApprovalService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Loan updateLoanStatus(Integer loanId, LoanStatus newStatus) {
        Loan loan = em.find(Loan.class, loanId);
        if (loan == null) {
            throw new IllegalArgumentException("Loan with id " + loanId + " not found.");
        }

        if (loan.getStatus() != LoanStatus.REQUESTED) {
            throw new IllegalStateException("Loan is already " + loan.getStatus() + " and cannot be updated.");
        }

        loan.setStatus(newStatus);
        em.merge(loan);

        return loan;
    }

    @Override
    public Loan getLoanDetails() {
        return null;
    }
}
