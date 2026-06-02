package com.banking.exception;

public class WithdrawalNotAllowedException extends RuntimeException{

    public WithdrawalNotAllowedException(String message) {
        super(message);
    }
}
