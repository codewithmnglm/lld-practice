package com.lms.library;

import com.lms.fine.DailyFine;
import com.lms.record.IssueRecord;
import com.lms.book.Book;
import com.lms.book.BookCategory;
import com.lms.book.BookCopy;
import com.lms.exception.*;
import com.lms.user.User;

import java.time.LocalDate;
import java.util.*;

public class Library {

    private Map<Integer, Book> books = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private Map<String, List<IssueRecord>> records = new HashMap<>();

    public void addBook(Book book) {
        if (books.containsKey(book.getBookId())) {
            throw new BookException("Book already exists with id: " + book.getBookId());
        }
        books.put(book.getBookId(), book);
    }

    public void addUser(User user) {
        if (users.containsKey(user.getUserId())) {
            throw new UserException("User already exists with id: " + user.getUserId());
        }
        users.put(user.getUserId(), user);
    }

    public void removeBook(int bookId) {
        Book book = books.get(bookId);
        if (book == null) throw new BookNotFoundException(bookId);
        removeBookFromLibrary(book);
    }

    public boolean searchBookById(int bookId) {
        if (!books.containsKey(bookId)) throw new BookNotFoundException(bookId);
        return true;
    }

    public void issueBook(int bookId, String userId, LocalDate issueDate) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null) throw new BookNotFoundException(bookId);
        if (user == null) throw new UserNotFoundException(userId);

        if (!user.canBorrowBooks()) {
            throw new IssueNotAllowedException(userId + " is not allowed to borrow books");
        }

        if (!book.canIssue()) {
            throw new BookCantBeIssuedException(book.getName());
        }


        if (user.getIssuedBooks().size() >= user.getMaxBooksAllowed()) {
            throw new MaximumBooksLimitReachedException(userId);
        }


        BookCopy copy = book.issueACopy();
        user.addIssuedBook(copy);
        IssueRecord issueRecord = new IssueRecord(copy, userId, issueDate, issueDate.plusDays(book.getMaxIssueDays()), null);
        records.computeIfAbsent(userId, k -> new ArrayList<>()).add(issueRecord);
        System.out.println("BookId " + bookId + " successfully issued to UserId " + userId);


    }

    public void returnBook(int bookId, String userId, int bookCopyId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null) throw new BookNotFoundException(bookId);
        if (user == null) throw new UserNotFoundException(userId);
        IssueRecord issueRecord = getRecordFromUserIdAndBookCopyId(userId, bookCopyId);
        book.returnACopy(bookCopyId);
        user.removeIssuedBook(bookCopyId);
        issueRecord.setReturnedDate(LocalDate.now());
        double fine = issueRecord.calculateFine(new DailyFine());
        System.out.println("Total Fine: " + fine);

    }

    public boolean searchBookByAuthor(String author) {
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            if (entry.getValue().getAuthor().equalsIgnoreCase(author)) return true;
        }
        return false;
    }

    public boolean searchBookByName(String name) {
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public List<Book> fetchBooksByCategory(BookCategory category) {
        List<Book> booksList = new ArrayList<>();
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            if (entry.getValue().getCategory().equals(category)) {
                booksList.add(entry.getValue());
            }
        }
        return booksList;
    }

    public List<Book> fetchBooksByCategory(String category) {
        return fetchBooksByCategory(BookCategory.valueOf(category.toUpperCase()));
    }

    public void removeBookFromLibrary(Book book) {
        if (book.hasIssuedCopies()) {
            throw new BookException("Book has issued copies and cannot be removed: " + book.getBookId());
        }
        books.remove(book.getBookId());
    }

    public void removeUser(User user) {
        users.remove(user.getUserId());
    }

    public int totalBooks() {
        int size = 0;
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            size += entry.getValue().getTotalCopies();
        }
        return size;
    }

    public int totalAvailableCopies() {
        int size = 0;
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            size += entry.getValue().getAvailableCopies();
        }
        return size;
    }

    public List<Book> returnAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            if (entry.getValue().isAvailable()) availableBooks.add(entry.getValue());
        }
        return availableBooks;
    }

    public List<Book> returnIssuedBooks() {
        List<Book> issuedBooks = new ArrayList<>();
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            if (entry.getValue().hasIssuedCopies()) issuedBooks.add(entry.getValue());
        }
        return issuedBooks;
    }

    public List<BookCopy> getIssuedBooksByUser(String userId) {
        User user = users.get(userId);
        if (user == null) throw new UserNotFoundException(userId);
        return user.getIssuedBooks();
    }

    public List<IssueRecord> getRecordsForUser(String userId) {
        return Collections.unmodifiableList(records.getOrDefault(userId, List.of()));
    }

    public IssueRecord getRecordFromUserIdAndBookCopyId(String userId, int bookCopyId) {
        List<IssueRecord> userRecords = records.getOrDefault(userId, List.of());
        for (IssueRecord record : userRecords) {
            if (record.getBookCopy().getBookCopyId() == bookCopyId && record.getReturnedDate() == null) {
                return record;
            }
        }
        throw new RecordNotFoundException(userId, bookCopyId);
    }

}
