package com.lms.exception;

public class BookNotAvailableException extends BookException {
    public BookNotAvailableException(int bookId) {
        super("Book not available: " + bookId);
    }
}
