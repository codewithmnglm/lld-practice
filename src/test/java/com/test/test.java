package com.test;

import com.lms.book.*;
import com.lms.exception.BookCantBeIssuedException;
import com.lms.library.Library;
import com.lms.user.Student;
import com.lms.user.User;

import java.time.LocalDate;

public class test {

    public static void main(String[] args) throws Exception {

        Book b1 = new Ebook("MathsForClass10", "R D Sharma", 1,2, BookCategory.MATHS);
        Book b2 = new PhysicalBook("PhysicsForClass10", "H C Verma", 2,3,BookCategory.SCIENCE);
        Book b3 = new ReferenceBook("ChemForClass10", "R S Chand", 3,1,BookCategory.SCIENCE);
        User user = new Student("007", "Mangalam");

        Library library = new Library();
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        System.out.println("Total books: " + library.totalBooks());
        System.out.println("Available copies: " + library.totalAvailableCopies());
        library.addUser(user);
        library.issueBook(1, "007",LocalDate.now());

        library.issueBook(2, "007",LocalDate.now());
        try {
            library.issueBook(3, "007",LocalDate.now());
        } catch (BookCantBeIssuedException exception) {
            System.out.println(exception.getMessage());
        }
       // library.returnBook(1,"007",101);


    }
}
