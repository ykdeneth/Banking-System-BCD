package com.ydm.j2ee.core.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Loan.findByAccountNo",
                query = "SELECT l FROM Loan l WHERE l.account=:accountNo"),
        @NamedQuery(name = "Loan.findByAccountAndEmail", query = "SELECT l FROM Loan l " +
                "JOIN l.account a " +
                "JOIN a.user u " +
                "WHERE u.email = :email AND a.accountNo = :accno"),

})
@Table(name = "loan")
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String loanAmount;

    @Column(nullable = false)
    private Integer durationMonths;

    @ManyToOne
    @JoinColumn(
            name = "accountNo",           // FK column in loan table
            referencedColumnName = "accountNo"
    )
    private Account account;
    private double paidAmount;
    private double payableAmount;
    public Loan() {
    }

    public Loan(Integer id, String loanAmount, Integer durationMonths, Account account, double paidAmount, double payableAmount) {
        this.id = id;
        this.loanAmount = loanAmount;
        this.durationMonths = durationMonths;
        this.account = account;
        this.paidAmount = paidAmount;
        this.payableAmount = payableAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }
}
