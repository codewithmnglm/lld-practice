package com.banking.transaction;

import java.time.LocalDateTime;
import java.util.Date;

public class Transaction {

    private final double amount;
    private final TransactionType type;
    private final LocalDateTime txnDateTime;
    private final String customerId;

    public Transaction(double amount, TransactionType type, LocalDateTime txnDateTime, String customerId) {
        this.amount = amount;
        this.type = type;
        this.txnDateTime = txnDateTime;
        this.customerId = customerId;

    }
    public String getCustomerId() {
        return customerId;
    }

    public LocalDateTime getTxnDateTime() {
        return txnDateTime;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }






}
