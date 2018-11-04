package fr.d2factory.libraryapp.book;

import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.member.Member;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.lang.Math.abs;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository implements Library{
    private Map<Double, Book> availableBooks = new HashMap<>();
    private Map<Book, LocalDate> borrowedBooks = new HashMap<>();
    private Map<Member,Book> memberBorrowedBooks = new HashMap<>();

    public void addBooks(List<Book> books){

        for ( Book book: books) {
            double isbn = book.getIsbn();
            availableBooks.put(isbn,book);
        }
    }

    public void addBook(Book book){
        double isbn = book.getIsbn();
     availableBooks.put(isbn,book);
    }

    public Book findBook(double isbnCode) {
        return availableBooks.get(isbnCode);
    }

    public Boolean hasBook(double isbnCode) {
        return (availableBooks.get(isbnCode) != null) ?  true : false;
    }

    public Book findMemberBorrowedBook(Member member) {
        return memberBorrowedBooks.get(member);
    }

    public void saveBookBorrow(Book book, Member member,LocalDate borrowedAt) {
        borrowedBooks.put(book, borrowedAt);
        memberBorrowedBooks.put(member,book);
        availableBooks.remove(book.getIsbn());
    }

    public LocalDate findBorrowedBookDate(Book book) {
         return borrowedBooks.get(book);
    }

    @Override
    public void borrowBook(double isbnCode, Member member , LocalDate borrowedAt) throws HasLateBooksException {
        if (canBorrowBook(member) == false ){
            throw new HasLateBooksException();
        }
        if (findBook(isbnCode)!= null){
            saveBookBorrow(findBook(isbnCode),member,borrowedAt);
            borrowedBooks.put(findBook(isbnCode),borrowedAt);
        }
    }

    public Boolean canBorrowBook(Member member){
        Collection<Book> values = memberBorrowedBooks.values();
        Boolean canBorrow = true;
        for (Book book:values){
            if (ChronoUnit.DAYS.between(borrowedBooks.get(book),LocalDate.now())>30){
                canBorrow = false;
            }
        }
        return canBorrow;
    }

    @Override
    public void returnBook(Book book, Member member) {
        long numberOfDays;
        LocalDate borrowedBookDate = findBorrowedBookDate(book);
        numberOfDays = ChronoUnit.DAYS.between(LocalDate.now(), borrowedBookDate);
        addBook(book);
        member.payBook((int) abs(numberOfDays));
    }
}
