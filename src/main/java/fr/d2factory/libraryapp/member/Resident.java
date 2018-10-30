package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;


import java.time.LocalDate;

public class Resident extends Member {
    @Override
    public void payBook(int numberOfDays) {
        double payment = 0;
        if (numberOfDays > 30) {
            payment = 30 * 0.1 + numberOfDays % 30 * 0.2;
        }
        this.setWallet((float) (getWallet() - payment));

    }
}
