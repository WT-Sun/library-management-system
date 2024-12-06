package src;

import java.time.LocalDate;

public class RomantiqueBookBuilder implements BookBuilder{
    Book book;

    public RomantiqueBookBuilder() {
        this.book = new Book();
    }
    public void buildIsbn()
    {
        this.book.setIsbn("003");
    }
    public void buildAuthor()
    {
        this.book.setAuthor("Jane Wood");
    }
    public void buildTitle()
    {
        this.book.setTitle("Romance");
    }
    public void buildType()
    {
        this.book.setBooktype("Romantique");
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
