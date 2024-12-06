package src;

import java.time.LocalDate;

public class ActionBuilder implements BookBuilder{
    private Book book ;

    public ActionBuilder() {
        this.book = new Book();
    }
    public void buildIsbn()
    {
        this.book.setIsbn("001");
    }
    public void buildAuthor()
    {
        this.book.setAuthor("Jane Smith");
    }
    public void buildTitle()
    {
        this.book.setTitle("Action Mystery");
    }
    public void buildType()
    {
        this.book.setBooktype("Action");
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
