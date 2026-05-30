package com.lms.user;

import com.lms.book.Book;
import com.lms.book.BookCopy;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private String userId;
    private String userName;
    private List<BookCopy> issuedBooks = new ArrayList<>();

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }


    public abstract int getMaxBooksAllowed();   // each type answers differently

    public abstract int getMaxIssueDays();

    public abstract boolean canManageBooks();

    public abstract boolean canBorrowBooks();

    public void addIssuedBook(BookCopy copy) {
        issuedBooks.add(copy);
    }

    public void removeIssuedBook(int bookCopyId) {
        issuedBooks.removeIf(copy -> copy.getBookCopyId() == bookCopyId);
    }

    public boolean hasIssuedCopy(int bookCopyId) {
        return issuedBooks.stream().anyMatch(copy -> copy.getBookCopyId() == bookCopyId);
    }

    public List<BookCopy> getIssuedBooks() {
        return issuedBooks;
    }

}
