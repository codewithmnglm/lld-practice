package com.banking.exception;

public class WithdrawalNotAllowedException extends AccountException{

    public WithdrawalNotAllowedException(String message) {
        super(message);
    }
}
