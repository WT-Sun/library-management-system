package src;

import java.time.LocalDate;

public class ScientificBookBuilder implements BookBuilder{
    private Book book ;

    public ScientificBookBuilder() {
        this.book = new Book();
    }
    public void buildIsbn()
    {
        this.book.setIsbn("002");
    }
    public void buildAuthor()
    {
        this.book.setAuthor("John Doe");
    }
    public void buildTitle()
    {
        this.book.setTitle("Scientific Adventure");
    }
    public void buildType()
    {
        this.book.setBooktype("Scientific");
    }
    public void buildAvailable()
    {
        this.book.setAvailable(true);
    }
    public void buildBorrowDate()
    {
        this.book.setBorrowDate(LocalDate.now());
    }
    public void buildDueDate()
    {
        this.book.setDueDate(LocalDate.now().plusWeeks(2));
    }
    public Book getBook()
    {
        return this.book;
    }
}


