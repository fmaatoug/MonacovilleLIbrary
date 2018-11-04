package fr.d2factory.libraryapp.book;

/**
 * A simple representation of a book
 */
public class Book {
    String title;
    String author;
    double isbn;

    public Book(String title, String author, double isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    public double getIsbn(){
        return this.isbn;
    }
}
