package com.lms.user;

import com.lms.constant.Constant;

public class Student extends User {

    public Student(String userId, String userName) {
        super(userId, userName);
    }

    @Override
    public int getMaxBooksAllowed() {
        return Constant.MAX_NO_OF_BOOKS_PER_STUDENT;
    }

    @Override
    public int getMaxIssueDays() {
        return Constant.MAX_ISSUE_DAYS_FOR_STUDENT;
    }

    @Override
    public boolean canManageBooks() {
        return false;
    }

    @Override
    public boolean canBorrowBooks() {
        return true;
    }
}
