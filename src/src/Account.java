package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {
    private String username;
    private String password;
    private final List<Book> BorrowedBooks;
    private final List<Book> ReturnedBooks;
    private PaymentState paymentState = new CashPayment();

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.BorrowedBooks = new ArrayList<>();
        this.ReturnedBooks = new ArrayList<>();
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public List<Book> getBorrowedBooks() {
        return BorrowedBooks;
    }
    public List<Book> getReturnedBooks() {return ReturnedBooks;}
    public void setBorrowedBook(Book borrowedBooks) {
        this.BorrowedBooks.add(borrowedBooks);
    }
    public void setReturnedBooks(Book returnedBooks) {
        this.ReturnedBooks.add(returnedBooks);
    }
    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }
    public String getPaymentState(){
        return paymentState.getPaymentMethod();
    }
    public void RemoveBorrowedBooks(Book borrowedBooks) {
        this.BorrowedBooks.remove(borrowedBooks);
    }
    public void payLateFees(double amount, Scanner scanner) {
        paymentState.payLateFees(amount);
        Receipt.PrintReceipt(scanner, this, amount);
    }
}
