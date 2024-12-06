package src;

import java.util.Map;

public interface BookRepository {
    void addBook(Book book);
    Book searchBookTitle(String title);
    Book searchBookISBN(String isbn);
    int getBookStock(String isbn);
    Map<String, Book> getAllBooks();



}
