package fr.d2factory.libraryapp.member;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import fr.d2factory.libraryapp.book.*;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Student  extends Member implements Library{

    private  int year;
    BookRepository bookRepository = new BookRepository();
    private Map<Book, LocalDate> myBorrowedBooks = new HashMap<>();

    public Student(int year){
        this.year = year;
    }


    @Override
    public double payBook(int numberOfDays) {
        double payment = 0 ;
        if (numberOfDays > 30){
            payment = 30 * 0.1 + numberOfDays % 30 * 0.15;
        }
        return payment;
    }

    public Boolean isFirstYear() {
        if (year == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void borrowBook(double isbnCode, LocalDate borrowedAt) throws HasLateBooksException {
        Collection<LocalDate> values = myBorrowedBooks.values();
        for (LocalDate localDate:values){
            if (Period.between(LocalDate.now(),localDate).getDays()>30){
                throw new HasLateBooksException();
            }
        }
        if (bookRepository.findBook(isbnCode)!= null){
            bookRepository.saveBookBorrow(bookRepository.findBook(isbnCode),borrowedAt);
            myBorrowedBooks.put(bookRepository.findBook(isbnCode),borrowedAt);
        }
    }

    @Override
    public void returnBook(Book book) {
        Period numberOfDays;
        LocalDate borrowedBookDate = bookRepository.findBorrowedBookDate(book);
        numberOfDays = Period.between(LocalDate.now(), borrowedBookDate);
        bookRepository.addBook(book);
        Double payment = payBook(numberOfDays.getDays());
    }
}
