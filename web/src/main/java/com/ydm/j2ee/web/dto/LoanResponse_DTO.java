package com.ydm.j2ee.web.dto;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class LoanResponse_DTO {

    @Expose
    private Long userId;

    @Expose
    private String userName;

    @Expose
    private String userEmail;

    @Expose
    private String accountNo;

    @Expose
    private Integer loanId;

    @Expose
    private double loanAmount;

    @Expose
    private String loanStatus;

    @Expose
    private Date requestedDate;

    public LoanResponse_DTO() {}

    public LoanResponse_DTO(com.ydm.j2ee.core.model.Loan loan) {
        // From loan entity
        this.loanId = loan.getId();
        this.loanAmount = loan.getAmount();
        this.loanStatus = loan.getStatus() != null ? loan.getStatus().name() : null;
        this.requestedDate = loan.getRequestedDate();

        // From loan's linked account and user
        if (loan.getAccount() != null) {
            this.accountNo = loan.getAccount().getAccountNo();
            if (loan.getAccount().getUser() != null) {
                this.userId = loan.getAccount().getUser().getId();
                this.userName = loan.getAccount().getUser().getName();
                this.userEmail = loan.getAccount().getUser().getEmail();
            }
        }
    }

    // getters and setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }
}
