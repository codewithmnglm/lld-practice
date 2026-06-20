package com.banking.exception;

public class TransferFailException extends RuntimeException{

    public TransferFailException(String message, Throwable cause) {
        super(message);
    }
}
