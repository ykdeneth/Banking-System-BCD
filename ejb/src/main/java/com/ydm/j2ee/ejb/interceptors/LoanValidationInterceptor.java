package com.ydm.j2ee.ejb.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.annotation.Priority;

import com.ydm.j2ee.core.model.Account;


public class LoanValidationInterceptor {

    @AroundInvoke
    public Object validateLoanRequest(InvocationContext ctx) throws Exception {
        Object[] params = ctx.getParameters();

        if (params.length > 0 && params[0] instanceof com.ydm.j2ee.core.model.Loan) {
            com.ydm.j2ee.core.model.Loan loan = (com.ydm.j2ee.core.model.Loan) params[0];
            Account account = loan.getAccount();

            // Basic validation example:
            if (account == null) {
                throw new IllegalArgumentException("Loan must be associated with an account.");
            }
            if (account.getBalance() < 1000) {  // example minimum balance needed
                throw new IllegalStateException("Account balance too low to request loan.");
            }
            if (account.getUser().getStatus() != com.ydm.j2ee.core.model.Status.ACTIVE) {
                throw new IllegalStateException("User account is not active.");
            }

            // Add more validations as needed...
        }
        // Proceed if checks pass
        return ctx.proceed();
    }
}
