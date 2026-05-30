package com.lms.record;

import com.lms.book.BookCopy;
import com.lms.fine.Fine;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class IssueRecord {

    private BookCopy bookCopy;

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    private String userId;
    private LocalDate issueDate;
    private LocalDate dueDate;

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    public double calculateFine(Fine fine) {
        if (returnedDate == null || !returnedDate.isAfter(dueDate)) return 0;
        long overdueDays = ChronoUnit.DAYS.between(dueDate, returnedDate);
        return fine.getTotalFine(overdueDays);
    }

    private LocalDate returnedDate;

    public IssueRecord(BookCopy copy, String userId, LocalDate issueDate ,LocalDate dueDate, LocalDate returnedDate) {
        this.bookCopy  = copy;
        this.userId    = userId;
        this.issueDate = issueDate;
        this.dueDate   = dueDate;
        this.returnedDate = returnedDate;
    }




}
