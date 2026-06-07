package com.banking.exception;

public class AccountClosedException extends AccountException {

    public AccountClosedException(String message) {
        super(message);
    }
}
