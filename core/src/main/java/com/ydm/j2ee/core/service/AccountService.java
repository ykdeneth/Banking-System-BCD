package com.ydm.j2ee.core.service;

import com.ydm.j2ee.core.model.Account;
import com.ydm.j2ee.core.model.TransferDetails;
import com.ydm.j2ee.core.model.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    void creditToAccount(String accountNo, double amount);
    boolean debitFromAccount(String accountNo, double amount);
    boolean createAccount(User user, double initialBalance);
    Account deleteAccount(String accountNo);
    boolean transfer(String accountNo1, String accountNo2, double amount, String reason, String type);
    boolean addInterestToAllAccounts(double interestRatePercent);
    List<TransferDetails> getTransactionsByUserEmail(String email);
    Optional<Account> findByAccountNo(String accountNo);
}
