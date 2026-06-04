package com.banking.exception;

public class DepositNotAllowedException extends AccountException {

    public DepositNotAllowedException(String message) {
        super(message);
    }

}
