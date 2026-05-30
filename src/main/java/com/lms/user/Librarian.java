package com.lms.user;

import com.lms.constant.Constant;

public class Librarian extends User {

    public Librarian(String userId, String userName) {
        super(userId, userName);
    }

    @Override
    public int getMaxBooksAllowed() {
        return Constant.MAX_NO_OF_BOOKS_PER_LIBRARIAN;
    }

    @Override
    public int getMaxIssueDays() { return Constant.MAX_ISSUE_DAYS_FOR_LIBRARIAN; }

    @Override
    public boolean canManageBooks() {
        return true;
    }

    @Override
    public boolean canBorrowBooks() {
        return false;
    }
}
