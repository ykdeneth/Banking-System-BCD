package com.ydm.j2ee.web.dto;

import com.google.gson.annotations.Expose;
import com.ydm.j2ee.core.model.TransferDetails;

import java.util.Date;

public class TransferDetailsDTO {
    @Expose
    private Long id;

    @Expose
    private String fromName;

    @Expose
    private String toName;

    @Expose
    private double amount;

    @Expose
    private String reason;

    @Expose
    private Date transactionDate;

    @Expose
    private String transactionType;

    @Expose
    private String fromAccountNo;

    @Expose
    private String toAccountNo;

    public TransferDetailsDTO() {
    }

    // Constructor to map from TransferDetails entity
    public TransferDetailsDTO(TransferDetails t) {
        this.id = t.getId();
        this.fromName = t.getFrom_name();
        this.toName = t.getTo_name();
        this.amount = t.getAmount();
        this.reason = t.getReason();
        this.transactionDate = t.getTransactionDate();
        this.transactionType = t.getTransactionType() != null ? t.getTransactionType().name() : null;
        this.fromAccountNo = t.getFromAccountNo();
        this.toAccountNo = t.getToAccountNo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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
// getters, setters omitted for brevity
}
