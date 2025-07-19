package com.ydm.j2ee.web.dto;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class LoanDetailsResponse_DTO {

    @Expose
    private String userName;

    @Expose
    private String userEmail;

    @Expose
    private String accountNo;

    @Expose
    private Integer loanId;

    @Expose
    private double amount;

    @Expose
    private String status;

    @Expose
    private Date requestedDate;

    public LoanDetailsResponse_DTO() {}

    public LoanDetailsResponse_DTO(com.ydm.j2ee.core.model.Loan loan) {
        if (loan.getAccount() != null && loan.getAccount().getUser() != null) {
            this.userName = loan.getAccount().getUser().getName();
            this.userEmail = loan.getAccount().getUser().getEmail();
            this.accountNo = loan.getAccount().getAccountNo();
        }
        this.loanId = loan.getId();
        this.amount = loan.getAmount();
        this.status = loan.getStatus() != null ? loan.getStatus().name() : null;
        this.requestedDate = loan.getRequestedDate();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }
}
