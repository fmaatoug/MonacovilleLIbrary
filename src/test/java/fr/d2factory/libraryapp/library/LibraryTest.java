package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.book.ISBN;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {
    private Library library ;
    private BookRepository bookRepository;
    private ISBN isbn;
    private Student student;

    @Before
    public void setup(){
        //TODO instantiate the library and the repository
        bookRepository = new BookRepository();
        List<Book> booklist = new ArrayList<Book>();
        Book book1 = new Book("Harry Potter","J.K. Rowling",465789645);
        Book book2 = new Book("Around the world in 80 days","Jules Verne",332645646);
        Book book3 = new Book("Catch 22","Joseph Heller",968787565);
        Book book4 = new Book("La peau de chagrin","Balzac",465789453);
        Book book5 = new Book("The Stranger","Albert Camus",465789454);
        Book book6 = new Book("The Plague","Albert Camus",465789455);
        Book book7 = new Book("The Fall","Albert Camus",465789456);
        Book book8 = new Book("A Happy Death","Albert Camus",465789457);
        booklist.add(book1);
        booklist.add(book2);
        booklist.add(book3);
        booklist.add(book4);
        booklist.add(book5);
        booklist.add(book6);
        booklist.add(book7);
        booklist.add(book8);

        bookRepository.addBooks(booklist);


        //TODO add some test books (use BookRepository#addBooks)
        //TODO to help you a file called books.json is available in src/test/resources
    }

    @Test
    public void member_can_borrow_a_book_if_book_is_available(){
        Student student1 = new Student(2);
        ISBN isbn = new ISBN(465789645);
        LocalDate localDate = LocalDate.now();
        bookRepository.borrowBook(isbn.getIsbnCode(), student1 , localDate);
        assertTrue(bookRepository.findMemberBorrowedBook(student1).getIsbn()==isbn.getIsbnCode());
    }

    @Test
    public void borrowed_book_is_no_longer_available(){
        Student student2 = new Student(1);
        ISBN isbn = new ISBN(332645646);
        LocalDate localDate = LocalDate.now();
        bookRepository.borrowBook(isbn.getIsbnCode(), student2 , localDate);
        assertFalse(bookRepository.hasBook(isbn.getIsbnCode()));
    }

    @Test
    public void residents_are_taxed_10cents_for_each_day_they_keep_a_book(){

        Resident resident = new Resident();
        resident.setWallet(1000);
        ISBN isbn = new ISBN(465789453);
        bookRepository.borrowBook(isbn.getIsbnCode(),resident,LocalDate.now().minusDays( 30 ));
        bookRepository.returnBook(bookRepository.findMemberBorrowedBook(resident),resident);
        assertFalse(resident.getWallet()== 1000);
    }

    @Test
    public void students_pay_10_cents_the_first_30days(){
        Student student = new Student(3);
        student.setWallet(3);
        ISBN isbn = new ISBN(968787565);
        bookRepository.borrowBook(isbn.getIsbnCode(),student,LocalDate.now().minusDays( 30 ));
        bookRepository.returnBook(bookRepository.findMemberBorrowedBook(student),student);
        assertTrue(student.getWallet()== 0);
    }

    @Test
    public void students_in_1st_year_are_not_taxed_for_the_first_15days(){

        Student student = new Student(1);
        student.setWallet(3);
        ISBN isbn = new ISBN(465789457);
        bookRepository.borrowBook(isbn.getIsbnCode(),student,LocalDate.now().minusDays( 15 ));
        bookRepository.returnBook(bookRepository.findMemberBorrowedBook(student),student);
        assertTrue(student.getWallet()== 3);
    }

    @Test
    public void students_pay_15cents_for_each_day_they_keep_a_book_after_the_initial_30days(){

        Student student = new Student(3);
        student.setWallet((float) 4.5);
        ISBN isbn = new ISBN(465789456);
        bookRepository.borrowBook(isbn.getIsbnCode(),student,LocalDate.now().minusDays( 40 ));
        bookRepository.returnBook(bookRepository.findMemberBorrowedBook(student),student);
        assertTrue(student.getWallet()== 0);
    }

    @Test
    public void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days(){
        Resident resident = new Resident();
        resident.setWallet(7);
        ISBN isbn = new ISBN(465789453);
        bookRepository.borrowBook(isbn.getIsbnCode(),resident,LocalDate.now().minusDays( 65 ));
        bookRepository.returnBook(bookRepository.findMemberBorrowedBook(resident),resident);
        assertTrue(resident.getWallet()== 0);
    }

    @Test
    public void members_cannot_borrow_book_if_they_have_late_books(){

        Student student = new Student(3);
        student.setWallet(45);
        ISBN isbn = new ISBN(465789454);
        bookRepository.borrowBook(isbn.getIsbnCode(),student,LocalDate.now().minusDays( 31 ));
        assertFalse(bookRepository.canBorrowBook(student));
    }

}
