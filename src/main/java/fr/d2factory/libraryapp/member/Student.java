package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.book.*;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;

import java.time.LocalDate;
import java.time.Period;

public class Student  extends Member implements Library{

    private  int year;
    BookRepository bookRepository = new BookRepository();
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
    public void borrowBook(double isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
        if (bookRepository.findBook(isbnCode)!= null){
            bookRepository.saveBookBorrow(bookRepository.findBook (isbnCode),borrowedAt);
        }
    }

    @Override
    public void returnBook(Book book, Member member) {
        int numberOfDays = 0;
        LocalDate borrowedBookDate = bookRepository.findBorrowedBookDate(book);
        numberOfDays = new Period(LocalDate.now(), borrowedBookDate);

    }
}
