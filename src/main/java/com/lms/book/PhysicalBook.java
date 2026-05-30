package com.lms.book;

import com.lms.constant.Constant;

public class PhysicalBook extends Book {

    public PhysicalBook(String name, String author, int bookId, int count, BookCategory category) {
        super(name, author, bookId, count, category);
    }

    @Override
    public boolean canIssue() {
        return true;
    }

    @Override
    public int getMaxIssueDays() {
        return Constant.PHYSICAL_BOOK_ISSUE_DAYS;
    }
}
