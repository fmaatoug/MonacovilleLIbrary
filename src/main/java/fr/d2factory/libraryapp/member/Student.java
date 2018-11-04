package fr.d2factory.libraryapp.member;


public class Student  extends Member {

    private  int year;
    Boolean isStudent;

    public Student(int year){
        this.year = year;
        this.isStudent = true;
    }


    @Override
    public void payBook(int numberOfDays) {
        double payment = 0 ;
        if (isFirstYear()){
            numberOfDays %= 15;
        }
        if (numberOfDays > 30){
            payment = 30 * 0.1 + numberOfDays % 30 * 0.15;
        }else {
            payment = numberOfDays * 0.1;
        }
        this.setWallet((float) (getWallet()- payment));
    }

    public Boolean isFirstYear() {
        if (year == 1) {
            return true;
        }
        return false;
    }

    public Boolean getIsStudent(){
        return this.isStudent;
    }

}
