package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.member.Student;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class LibraryTest {
    private Library library ;
    private BookRepository bookRepository;

    @Before
    public void setup(){
        //TODO instantiate the library and the repository
        BookRepository bookRepository = new BookRepository();
        ObjectMapper mapper = new ObjectMapper();
        List<Book> booklist = new ArrayList<Book>();
        try {
            mapper.writeValue(new File("/home/exo/career/devoteam/recruitment-web/src/test/resources/books.json"), booklist);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bookRepository.addBooks(booklist);
        Student student2 = new Student(2);
        Student student1 = new Student(1);

        //TODO add some test books (use BookRepository#addBooks)
        //TODO to help you a file called books.json is available in src/test/resources
    }

    @Test
    public void member_can_borrow_a_book_if_book_is_available(){
        Student student = new Student(1);
    }

    @Test
    public void borrowed_book_is_no_longer_available(){
        fail("Implement me");
    }

    @Test
    public void residents_are_taxed_10cents_for_each_day_they_keep_a_book(){
        fail("Implement me");
    }

    @Test
    public void students_pay_10_cents_the_first_30days(){
        fail("Implement me");
    }

    @Test
    public void students_in_1st_year_are_not_taxed_for_the_first_15days(){
        fail("Implement me");
    }

    @Test
    public void students_pay_15cents_for_each_day_they_keep_a_book_after_the_initial_30days(){
        fail("Implement me");
    }

    @Test
    public void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days(){
        fail("Implement me");
    }

    @Test
    public void members_cannot_borrow_book_if_they_have_late_books(){
        fail("Implement me");
    }
}
