package src;

public interface BookBuilder {
    void buildIsbn();
    void buildAuthor();
    void buildTitle();
    void buildType();
    void buildAvailable();
    void buildBorrowDate();
    void buildDueDate();
    Book  getBook();
}
