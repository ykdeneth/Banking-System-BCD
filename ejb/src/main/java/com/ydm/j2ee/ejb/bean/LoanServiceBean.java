package com.ydm.j2ee.ejb.bean;

import com.ydm.j2ee.core.model.Loan;
import com.ydm.j2ee.core.model.Account;
import com.ydm.j2ee.core.service.AccountService;
import com.ydm.j2ee.core.service.LoanService;

import com.ydm.j2ee.ejb.interceptors.LoanValidationInterceptor;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.ejb.EJB;
import jakarta.interceptor.Interceptors;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Stateless
@Interceptors(LoanValidationInterceptor.class)
public class LoanServiceBean implements LoanService {

    @EJB
    private AccountService accountService; // For fetching account by ID or number

    @PersistenceContext
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Loan requestLoan(String accountNo, double amount, String email) {
        Account account = accountService.findByAccountNo(accountNo)
                .orElseThrow(() -> new IllegalArgumentException("Account does not exist"));
        List<Account> accounts = em.createNamedQuery("Account.findByAccountAndEmail", Account.class)
                .setParameter("accountNo2", accountNo)
                .setParameter("email", email)
                .getResultList();

        if (accounts.isEmpty()) {
            return null;
        } else {

            Loan loan = new Loan(amount, account);
            // Persist loan (assuming using container-managed EntityManager injected in this bean)
            System.out.println(loan.toString());
            em.persist(loan);

            // Add loan to account's loan list (optional, managed by JPA)
            account.getLoans().add(loan);

            return loan;
        }
//        return null;
    }

    // @PersistenceContext EntityManager em; injected here normally
}
