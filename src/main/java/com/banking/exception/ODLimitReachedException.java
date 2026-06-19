package com.banking.exception;

public class ODLimitReachedException extends WithdrawalNotAllowedException {

    public ODLimitReachedException(String message) {
        super(message);
    }
}
