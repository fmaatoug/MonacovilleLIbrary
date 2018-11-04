package fr.d2factory.libraryapp.book;

public class ISBN {
    double isbnCode;

    public ISBN() {
        this.isbnCode = Math.random()*1000000;
    }

    public ISBN( double isbnCode) {
        this.isbnCode = isbnCode;
    }

    public double getIsbnCode(){
        return this.isbnCode;
    }
}
