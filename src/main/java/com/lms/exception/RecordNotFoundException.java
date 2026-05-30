package com.lms.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message, int bookCopyId) {
        System.out.println("No record found with userId: " + message + " and bookCopyId: " + bookCopyId);
    }

}
