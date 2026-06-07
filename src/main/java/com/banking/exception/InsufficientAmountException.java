package com.banking.exception;

public class InsufficientAmountException extends TransactionException {

    public InsufficientAmountException(String message) {
        super(message);
    }
}
