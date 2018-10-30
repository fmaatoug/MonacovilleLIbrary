package fr.d2factory.libraryapp.book;

import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository implements Library{
    private Map<ISBN, Book> availableBooks = new HashMap<>();
    private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

    public void addBooks(List<Book> books){

        for ( Book book: books) {
            ISBN isbn = new ISBN();
            availableBooks.put(isbn,book);
        }
    }

    public void addBook(Book book){
        ISBN isbn = new ISBN();

     availableBooks.put(isbn,book);
    }

    public Book findBook(double isbnCode) {
        return availableBooks.get(isbnCode);
    }

    public void saveBookBorrow(Book book, LocalDate borrowedAt) {
        borrowedBooks.put(book, borrowedAt);
        availableBooks.remove(book);
    }

    public LocalDate findBorrowedBookDate(Book book) {
         return borrowedBooks.get(book);
    }

    @Override
    public void borrowBook(double isbnCode, Member member ,LocalDate borrowedAt) throws HasLateBooksException {
        Collection<LocalDate> values = borrowedBooks.values();
        for (LocalDate localDate:values){
            if (Period.between(LocalDate.now(),localDate).getDays()>30){
                throw new HasLateBooksException();
            }
        }
        if (findBook(isbnCode)!= null){
            saveBookBorrow(findBook(isbnCode),borrowedAt);
            borrowedBooks.put(findBook(isbnCode),borrowedAt);
        }
    }

    @Override
    public void returnBook(Book book, Member member) {
        Period numberOfDays;
        LocalDate borrowedBookDate = findBorrowedBookDate(book);
        numberOfDays = Period.between(LocalDate.now(), borrowedBookDate);
        addBook(book);
        member.payBook(numberOfDays.getDays());
    }
}
