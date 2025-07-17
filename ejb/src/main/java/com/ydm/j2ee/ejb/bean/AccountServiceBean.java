package com.ydm.j2ee.ejb.bean;

import com.ydm.j2ee.core.model.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import com.ydm.j2ee.core.service.AccountService;
import jakarta.persistence.TypedQuery;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
public class AccountServiceBean implements AccountService {

    @PersistenceContext
    private EntityManager em;


    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void creditToAccount(String accountNo, double amount) {
        try {
            Account account = em.createNamedQuery("Account.findByAccountNo", Account.class)
                    .setParameter("accountNo", accountNo)
                    .getSingleResult();
            if (amount > 0) {
                account.setBalance(account.getBalance() + amount);
            }
            em.merge(account);
        } catch (NoResultException e) {
            e.printStackTrace();
        }

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public boolean debitFromAccount(String accountNo, double amount) {
        try {
            Account account = em.createNamedQuery("Account.findByAccountNo", Account.class)
                    .setParameter("accountNo", accountNo)
                    .getSingleResult();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                em.merge(account);
                return true;
            }
            return false;
        } catch (NoResultException e) {
            e.printStackTrace();
            return false;
        }

    }

    @RolesAllowed("ADMIN")
    @Override
    public boolean createAccount(User user, double initialBalance) {
        if (user != null) {
            Account account = new Account();
            account.setAccountNo(generateAccountNo());
            account.setBalance(initialBalance);
            user.setStatus(Status.ACTIVE);
            User managedUser = em.merge(user);

            account.setUser(managedUser);
            if(user.getVerifyState().equals(VerifyState.NOT_VERIFIED_ACCOUNT)){
                return false;
            }else {
            em.persist(account);
            System.out.println("Created account " + account.getAccountNo()+ " with balance " + account.getBalance() + user.getEmail());
            return true;
            }
        }
        return false;

    }

    @RolesAllowed("ADMIN")
    @Override
    public Account deleteAccount(String accountNo) {

        return null;
    }

    @Override
    public boolean transfer(String accountNo1, String accountNo2, double amount, String reason, String type) {

        String user1 = checkAndPrintUserName(em, accountNo1);
        String user2 = checkAndPrintUserName(em, accountNo2);

        Account fromAccount = getAccountByAccountNo(accountNo1);
        Account toAccount = getAccountByAccountNo(accountNo2);

        if (fromAccount == null || toAccount == null) {
            System.out.println("One or both accounts not found");
            return false;
        }
        String fromAccountNo = fromAccount.getAccountNo();
        String toAccountNo = toAccount.getAccountNo();
        TransferDetails transferDetails = new TransferDetails();
        transferDetails.setFrom_name(user1);
        transferDetails.setTo_name(user2);
        transferDetails.setAmount(amount);
        transferDetails.setReason(reason);
        transferDetails.setFromAccount(fromAccount);
        transferDetails.setToAccount(toAccount);
        transferDetails.setTransactionDate(new Date());
        transferDetails.setFromAccountNo(fromAccountNo);
        transferDetails.setToAccountNo(toAccountNo);
        if(type!=null && type.equals("ON_TIME")){
            transferDetails.setTransactionType(TransactionType.ON_TIME);
            em.persist(transferDetails);
        }else if(type!=null && type.equals("SCHEDULED")){
            transferDetails.setTransactionType(TransactionType.SCHEDULED);
            em.persist(transferDetails);
        }else if(type!=null && type.equals("INTERESTED_RATE")){
            transferDetails.setTransactionType(TransactionType.INTERESTED_RATE);
            em.persist(transferDetails);
        }else {
            System.out.println("Invalid transfer type");
        }



        return false;
    }



    private String checkAndPrintUserName(EntityManager em, String accountNo) {
        try {
            String userName = em.createNamedQuery("User.findNameByAccountNo", String.class)
                    .setParameter("accountNo2", accountNo)
                    .getSingleResult();

            if (userName == null) {
                System.out.println("User name for account " + accountNo + " is null");
                return null;
            } else if (userName.trim().isEmpty()) {
                System.out.println("User name for account " + accountNo + " is empty");
                return "";
            } else {
                System.out.println("User name for account " + accountNo + ": " + userName);
                return userName;
            }
        } catch (NoResultException e) {
            System.out.println("User not found for account " + accountNo);
            return null;
        }
    }
    public Account getAccountByAccountNo(String accountNo) {
        try {
            return em.createNamedQuery("Account.findByAccountNo", Account.class)
                    .setParameter("accountNo", accountNo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public String generateAccountNo() {
        // Get current max accountNo from DB and increment, or use a random/sequence
        // For demo, generate random 6 digits and prefix '100'
        int random = (int) (Math.random() * 900000) + 100000; // ensures 6 digits
        return "100" + random;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean addInterestToAllAccounts(double interestRatePercent) {
        // Fetch all accounts
        List<Account> accounts = em.createNamedQuery("Account.findAll", Account.class)
                .getResultList();

        if (accounts.isEmpty()) {
            System.out.println("No accounts found to add interest.");
            return false;
        }

        for (Account account : accounts) {

            double currentBalance = account.getBalance();
            double interest = currentBalance * (interestRatePercent / 100.0);
            account.setBalance(currentBalance + interest);
            em.merge(account); // update the account in DB
            System.out.println("Added interest to account " + account.getAccountNo());
          try{
              TransferDetails transferDetails = new TransferDetails();

              Account toAccountID = getAccountByAccountNo(account.getAccountNo());

              transferDetails.setFrom_name(account.getUser().getName());
              transferDetails.setAmount(interest);
              transferDetails.setReason("Interested to " + account.getUser().getName());
              transferDetails.setTransactionDate(new Date());
              transferDetails.setToAccountNo(account.getAccountNo());
              transferDetails.setToAccount(toAccountID);
              transferDetails.setTransactionType(TransactionType.INTERESTED_RATE);
              em.persist(transferDetails);
          }catch(Exception e){
              System.out.println("Error adding interest to account " + account.getAccountNo());
          }


        }
        return false;
    }

    @Override
    public List<TransferDetails> getTransactionsByUserEmail(String email) {
        try {
            System.out.println("in getTransactionsByUserEmail method");
            return em.createNamedQuery("TransferDetails.findByUserEmail", TransferDetails.class)
                    .setParameter("email", email)
                    .getResultList();
        } catch (NoResultException e) {
            System.out.println("No transactions found for email " + email);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Account> findByAccountNo(String accountNo) {
        TypedQuery<Account> query = em.createNamedQuery("Account.findByAccountNo", Account.class);
        query.setParameter("accountNo", accountNo);
        try {
            Account account = query.getSingleResult();
            return Optional.of(account);
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }

    }
}
