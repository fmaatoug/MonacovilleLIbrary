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

public class Student  extends Member {

    private  int year;
    public Student(int year){
        this.year = year;
    }


    @Override
    public void payBook(int numberOfDays) {
        double payment = 0 ;
        if (numberOfDays > 30){
            payment = 30 * 0.1 + numberOfDays % 30 * 0.15;
        }
        this.setWallet((float) (getWallet()- payment));
    }

    public Boolean isFirstYear() {
        if (year == 1) {
            return true;
        }
        return false;
    }

}
