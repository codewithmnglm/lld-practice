package com.lms.book;

public class ReferenceBook extends Book{

    public ReferenceBook(String name, String author, int bookId, int count, BookCategory category) {
        super(name, author, bookId, count, category);
    }

    @Override
    public boolean canIssue() {
        return false;
    }

    @Override
    public int getMaxIssueDays() {
        return 0;
    }
}
