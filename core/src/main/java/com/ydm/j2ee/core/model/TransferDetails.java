package com.ydm.j2ee.core.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transfer")
@NamedQueries({
        @NamedQuery(
                name = "TransferDetails.findByUserEmail",
                query = "SELECT t FROM TransferDetails t " +
                        "JOIN t.fromAccount a " +
                        "JOIN a.user u " +
                        "WHERE u.email = :email " +
                        "ORDER BY t.transactionDate DESC"
        ),
})
public class TransferDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String from_name;
    private String to_name;
    private double amount;
    private String reason;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Account fromAccount;
    @ManyToOne(cascade = CascadeType.ALL)
    private Account toAccount;
    private String fromAccountNo;
    private String toAccountNo;

    public TransferDetails() {
    }

    public TransferDetails(Long id, String from_name, String to_name, double amount, String reason, Date transactionDate, TransactionType transactionType, Account fromAccount, Account toAccount, String fromAccountNo, String toAccountNo) {
        this.id = id;
        this.from_name = from_name;
        this.to_name = to_name;
        this.amount = amount;
        this.reason = reason;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.fromAccountNo = fromAccountNo;
        this.toAccountNo = toAccountNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public String getFromAccountNo() {
        return fromAccountNo;
    }

    public void setFromAccountNo(String fromAccountNo) {
        this.fromAccountNo = fromAccountNo;
    }

    public String getToAccountNo() {
        return toAccountNo;
    }

    public void setToAccountNo(String toAccountNo) {
        this.toAccountNo = toAccountNo;
    }
}
