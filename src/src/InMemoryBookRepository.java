package src;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class InMemoryBookRepository implements BookRepository {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Integer> bookStock = new HashMap<>();

    @Override
    public void addBook(Book book) {
        books.put(book.getIsbn(), book);

        // update the stock quantity for the book
        if (bookStock.containsKey(book.getIsbn())) {
            bookStock.put(book.getIsbn(), bookStock.get(book.getIsbn()) + 1);
        } else {
            bookStock.put(book.getIsbn(), 1);
        }
    }

    @Override
    public Book searchBookTitle(String title) {
        // search for a book by title
        return books.values().stream()
                .filter(book -> title.equals(book.getTitle()))
                .findFirst()
                .orElse(null);
    }


    public Book searchBookISBN(String isbn) {
        // search for a book by ISBN
        return books.values().stream()
                .filter(book -> isbn.equals(book.getIsbn()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int getBookStock(String isbn) {
        // get the stock quantity of a book
        return bookStock.getOrDefault(isbn, 0);
    }
    public void incrementBookStock(String isbn) {
        if (books.containsKey(isbn)) {
            int currentStock = bookStock.getOrDefault(isbn, 0);
            bookStock.put(isbn, currentStock + 1);
        }
    }
    public void decrementBookStock(String isbn) {
        if (books.containsKey(isbn)) {
            int currentStock = bookStock.getOrDefault(isbn, 0);
            if (currentStock > 0) {
                bookStock.put(isbn, currentStock - 1);
            }
        }
    }

    @Override
    public Map<String, Book> getAllBooks() {
        // return a copy of the books map
        return new HashMap<>(books);
    }
    public Map<String, Integer> getAllBookStock() {
        return new HashMap<>(bookStock);
    }

    public void alertOverdueBooks(Account user) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> lateBooks = new ArrayList<Book>();
        try {
            if (user.getBorrowedBooks() != null) {
                System.out.println("Current Date: " + LocalDate.now());
                for (Book borrowedBook : user.getBorrowedBooks()) {
                    System.out.println("Due Date: " + borrowedBook.getDueDate());
                    if (borrowedBook.getDueDate().isBefore(LocalDate.now())) {
                        System.out.println("Alert: The book with ISBN " + borrowedBook.getIsbn() +
                                " is overdue for user " + user.getUsername());
                        lateBooks.add(borrowedBook);
                    }
                    System.out.println("Late fees: $0.5 per day for each book");
                    System.out.println("Do you want to pay late fees? (Y/N)");
                    String choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("Y")) {
                        System.out.println("Select payment method:");
                        System.out.println("1. Cash");
                        System.out.println("2. Debit");
                        System.out.println("3. Credit");

                        int paymentChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (paymentChoice) {
                            case 1 -> {
                                user.payLateFees(calculateLateFees(lateBooks), scanner);
                            }
                            case 2 -> {
                                user.setPaymentState(new DebitPayment());
                                user.payLateFees(calculateLateFees(lateBooks), scanner);
                            }
                            case 3 -> {
                                user.setPaymentState(new CreditPayment());
                                user.payLateFees(calculateLateFees(lateBooks), scanner);
                            }
                            default -> System.out.println("Invalid payment method choice.");
                        }
                    }
                }
            }
        }
        catch (Exception e){
             if (user.getBorrowedBooks() == null) {
                 System.out.println("No username found");
             }
        }
    }

    private double calculateLateFees(ArrayList<Book> lateBooks) {
        long daysLate = 0;
        for (Book latebook : lateBooks){
            LocalDate dueDate = latebook.getDueDate();
            daysLate += ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        }
        return Math.round(daysLate * 0.5) ;
    }
}
