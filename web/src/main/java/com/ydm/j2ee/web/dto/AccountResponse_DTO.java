package com.ydm.j2ee.web.dto;

import com.google.gson.annotations.Expose;

public class AccountResponse_DTO {

    @Expose
    private Integer id;

    @Expose
    private String accountNo;

    @Expose
    private double balance;

    public AccountResponse_DTO() {
    }

    // Constructor to map Account entity to DTO
    public AccountResponse_DTO(com.ydm.j2ee.core.model.Account account) {
        this.id = account.getId();
        this.accountNo = account.getAccountNo();
        this.balance = account.getBalance();
        // Omitting user reference to avoid recursion / complexity
    }

    // Getters and setters omitted for brevity

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}

