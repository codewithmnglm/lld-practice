package com.lms.exception;

public class BookCantBeIssuedException extends BookException {

    public BookCantBeIssuedException(String bookId) {
        super("Refrence Book Cant be Issued " + bookId);
    }
}
