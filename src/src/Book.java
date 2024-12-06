package src;

import java.time.LocalDate;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;
    private LocalDate dueDate;
    private LocalDate borrowDate;
    private String Booktype;


    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setBorrowDate() {
        this.borrowDate = LocalDate.now();
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setDueDate() {
        this.dueDate = getBorrowDate().minusDays(14);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isAvailable(boolean b) {
        return isAvailable;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBooktype() {
        return Booktype;
    }

    public void setBooktype(String booktype) {
        Booktype = booktype;
    }

    @Override
    public String toString() {
        return "ISBN:'" + this.isbn +
                ", Title:'" + this.title +
                ", Author:'" + this.author +
                ", The type Of Book " + this.Booktype+
                ", Available:" + this.isAvailable +
                ", Borrow Date:" + this.borrowDate +
                ", Due Date:" + this.dueDate;
    }
}