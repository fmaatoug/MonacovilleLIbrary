package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;


import java.time.LocalDate;

public class Resident extends Member implements Library {
    @Override
    public void payBook(int numberOfDays) {

    }

    @Override
    public Book borrowBook(double isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
        return null;
    }

    @Override
    public void returnBook(Book book, Member member) {

    }
}
