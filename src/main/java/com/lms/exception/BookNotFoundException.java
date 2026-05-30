package com.lms.exception;

public class BookNotFoundException extends BookException {
    public BookNotFoundException(int bookId) {
        super("Book not found: " + bookId);
    }
}
