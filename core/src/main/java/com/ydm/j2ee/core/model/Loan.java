package com.ydm.j2ee.core.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "loan")
@NamedQueries({
        // Add NamedQuery if needed later
})
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status = LoanStatus.REQUESTED;

    @Temporal(TemporalType.TIMESTAMP)
    private Date requestedDate = new Date();

    // Relationship to Account (many loans belong to one account)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Loan() {}

    public Loan(double amount, Account account) {
        this.amount = amount;
        this.account = account;
        this.status = LoanStatus.REQUESTED;
        this.requestedDate = new Date();
    }

    // getters and setters

    public Integer getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Loan{" + "id=" + id + ", amount=" + amount + ", status=" + status + '}';
    }
}
