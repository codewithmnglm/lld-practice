package com.lms.book;

import com.lms.exception.BookNotAvailableException;

import java.util.ArrayList;
import java.util.List;

public abstract class Book {
    private String name;
    private String author;
    private int bookId;
    private int count;
    private int totalCopies;
    private BookCategory category;
    protected List<BookCopy> copies = new ArrayList<>();

    public Book(String name, String author, int bookId, int count, BookCategory category) {
        this.name = name;
        this.author = author;
        this.bookId = bookId;
        this.count = count;
        this.totalCopies = count;
        this.category=category;
        for (int i = 1; i <= count; i++) {
            int copyId = bookId * 100 + i;
            copies.add(new BookCopy(bookId, copyId));
        }
    }

    public abstract boolean canIssue();
    public abstract int getMaxIssueDays();

    public BookCopy issueACopy() {
        if (!canIssue())      throw new BookNotAvailableException(bookId);
        if (copies.isEmpty()) throw new BookNotAvailableException(bookId);
        if (count == 0)       throw new BookNotAvailableException(bookId);

        for (BookCopy copy : copies) {
            if (copy.isAvailable()) {
                copy.issueBook();
                count--;
                System.out.println("BookId " + bookId + " CopyId " + copy.getBookCopyId() + " issued");
                return copy;
            }
        }
        throw new BookNotAvailableException(bookId);
    }

    public void returnACopy(int bookCopyId) {

        for (BookCopy copy : copies) {
            if (copy.getBookCopyId() == bookCopyId) {
                copy.returnBook();
                count++;
                return;
            }
        }
        throw new BookNotAvailableException(bookCopyId);
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getCount() {
        return count;
    }

    public int getAvailableCopies() {
        return count;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getIssuedCopies() {
        return totalCopies - count;
    }

    public boolean hasIssuedCopies() {
        return getIssuedCopies() > 0;
    }

    public boolean areAllCopiesAvailable() {
        return !hasIssuedCopies();
    }

    public int getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return count > 0;
    }

    public BookCategory getCategory() {
        return category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return bookId == book.bookId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(bookId);
    }



}
