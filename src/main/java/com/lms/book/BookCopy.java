package com.lms.book;

import com.lms.exception.BookException;
import com.lms.exception.BookNotAvailableException;

public class BookCopy {

    private int bookId;
    private int bookCopyId;
    private boolean available;

    BookCopy(int bookId, int bookCopyId) {
        this.bookId = bookId;
        this.bookCopyId = bookCopyId;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    private void setAvailable(boolean available) {
        this.available = available;
    }

    public void issueBook() {
        if (!isAvailable()) throw new BookNotAvailableException(bookCopyId);
        setAvailable(false);
    }

    public void returnBook() {
        if (isAvailable()) throw new BookException("Book copy was never issued: " + bookCopyId);
        setAvailable(true);
        System.out.println("Book Id " + bookId + " Copy Id " + bookCopyId + " Returned");
    }

    public int getBookCopyId() {
        return bookCopyId;
    }
}
