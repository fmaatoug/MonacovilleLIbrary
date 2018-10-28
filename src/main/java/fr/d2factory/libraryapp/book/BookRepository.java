package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
    private Map<ISBN, Book> availableBooks = new HashMap<>();
    private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

    public void addBooks(List<Book> books){

        for ( Book book: books) {
            ISBN isbn = new ISBN();
            availableBooks.put(isbn,book);
        }
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
}
