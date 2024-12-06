package src;

public class assembleBook {
    BookBuilder bookBuilder;

    assembleBook(BookBuilder bookBuilder)
    {
        this.bookBuilder = bookBuilder;
    }
    void createBook()
    {
        this.bookBuilder.buildIsbn();
        this.bookBuilder.buildTitle();
        this.bookBuilder.buildAuthor();
        this.bookBuilder.buildType();
        this.bookBuilder.buildBorrowDate();
        this.bookBuilder.buildDueDate();
        this.bookBuilder.buildAvailable();
    }
    Book getBook()
    {
        return this.bookBuilder.getBook();
    }
}
