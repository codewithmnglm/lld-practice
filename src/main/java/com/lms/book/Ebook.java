package com.lms.book;

import com.lms.constant.Constant;

public class Ebook extends Book{


    public Ebook(String name, String author, int bookId, int count, BookCategory category) {
        super(name, author, bookId, count, category);
    }

    @Override
    public boolean canIssue() {
        return true;
    }

    @Override
    public int getMaxIssueDays() {
        return Constant.EBOOK_ISSUE_DAYS;
    }
}
