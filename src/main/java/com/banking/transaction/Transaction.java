package com.banking.transaction;

import java.time.LocalDateTime;
import java.util.Date;

public class Transaction {

    private double amount;
    private TransactionType type;
    private LocalDateTime txnDateTime;
    private String customerId;

    public Transaction(double amount, TransactionType type, LocalDateTime txnDateTime, String customerId) {
        this.amount = amount;
        this.type = type;
        this.txnDateTime = txnDateTime;
        this.customerId = customerId;

    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getTxnDateTime() {
        return txnDateTime;
    }

    public void setTxnDateTime(LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }





}
