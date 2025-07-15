package com.ydm.j2ee.ejb.bean;

import com.ydm.j2ee.core.service.AccountService;
import com.ydm.j2ee.core.service.TransferService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.transaction.*;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TransferServiceBean implements TransferService {

    @EJB
    private AccountService accountService;

    @Inject
    private UserTransaction userTransaction;

    @Override
    public void transferAmount(String sourceAccountNo, String destinationAccountNo, double amount, String reason, String type) {

        try {
            userTransaction.begin();
           boolean b = accountService.debitFromAccount(sourceAccountNo, amount);
           if(b){
            accountService.creditToAccount(destinationAccountNo, amount);
            userTransaction.commit();
               System.out.println("in process");
               boolean s = accountService.transfer(sourceAccountNo, destinationAccountNo, amount, reason, type);
               if(s){
               System.out.println("Transfer successful");
               }

           }else{
               System.out.println("Transfer failed - insufficient balance");
               userTransaction.rollback();
           }
        } catch (NotSupportedException e) {
            try {
                userTransaction.rollback();
            } catch (SystemException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        } catch (HeuristicRollbackException e) {
            throw new RuntimeException(e);
        } catch (HeuristicMixedException e) {
            throw new RuntimeException(e);
        } catch (RollbackException e) {
            throw new RuntimeException(e);
        }

    }
}
