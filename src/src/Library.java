package src;

import javax.security.auth.login.FailedLoginException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


public class Library {
    int choice;
    Scanner scanner;
    AccDB acc = new AccDB();
    InMemoryBookRepository repository = new InMemoryBookRepository();
    int count = 0;
    public void Start() {
        ActionBuilder actionBuilder = new ActionBuilder();
        assembleBook assembleBook = new assembleBook(actionBuilder);
        assembleBook.createBook();
        Book book1 = assembleBook.getBook();

        ScientificBookBuilder scientificBookBuilder = new ScientificBookBuilder();
        assembleBook assembleBook1 = new assembleBook(scientificBookBuilder);
        assembleBook1.createBook();
        Book book2 = assembleBook1.getBook();

        RomantiqueBookBuilder romantiqueBookBuilder = new RomantiqueBookBuilder();
        assembleBook assembleBook2 = new assembleBook(romantiqueBookBuilder);
        assembleBook2.createBook();
        Book book3 = assembleBook2.getBook();
        if (count == 0) {
            repository.addBook(book1);
            repository.addBook(book2);
            repository.addBook(book3);

        }

        System.out.println("\nAll books stock in the repository:");
        Map<String, Integer> allBookStock = ((InMemoryBookRepository) repository).getAllBookStock();
        allBookStock.forEach((isbn, stock) -> {
            System.out.println("ISBN: " + isbn + ", Stock: " + stock);
        });
        do {
            try {
                Menu1();
                //Before logging in
                switch (choice) {
                    case 1:
                        acc.registerAcc(scanner);
                    case 2:
                    do {
                        try {
                            System.out.println("Please enter your username:");
                            String username = scanner.nextLine();
                            System.out.println("Please enter your password:");
                            String password = scanner.nextLine();

                                Validation val = new Validation();
                            if (val.LogIn(username, password, acc)) {
                                System.out.println("You successfully logged in as: " + username);
                            }
                            else {
                                throw new FailedLoginException();
                            }
                            Account current = acc.getCurrentUser();
                            // Alert users
                            repository.alertOverdueBooks(current);
                            do {
                                try {
                                    System.out.println("""
                                            Please select among these options(1-3):
                                            1. Check User's Data
                                            2. Search Book
                                            3. Log out""");

                                    int choice1 = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (choice1) {
                                        case 1 -> {
                                            System.out.println("Username:");
                                            System.out.println(acc.getCurrentUser().getUsername());
                                            System.out.println("Password:");
                                            System.out.println(acc.getCurrentUser().getPassword());
                                            System.out.println("Borrowed Books:");
                                            System.out.println(acc.getCurrentUser().getBorrowedBooks());
                                            System.out.println("Returned Books:");
                                            System.out.println(acc.getCurrentUser().getReturnedBooks());

                                            System.out.println("Do you wish to edit your data?(Y/N)");
                                            String choice2 = scanner.nextLine();
                                            if (choice2.equalsIgnoreCase("Y")) {
                                                System.out.println("Enter your new username?");
                                                username = scanner.nextLine();
                                                acc.getCurrentUser().setUsername(username);
                                                System.out.println("Enter your new password?");
                                                password = scanner.nextLine();
                                                acc.getCurrentUser().setPassword(password);
                                            }
                                        }
                                        case 2 -> {
                                            try {
                                                System.out.println("""
                                                        Select filter(1-3):
                                                        1. Title
                                                        2. ISBN
                                                        3. Cancel""");
                                                int choice2 = scanner.nextInt();
                                                scanner.nextLine();

                                                switch (choice2) {
                                                    case 1 -> {
                                                        System.out.println("Enter book's title:");
                                                        String title = scanner.nextLine();
                                                        Book foundBook1 = repository.searchBookTitle(title);
                                                        if (foundBook1 != null) {
                                                            System.out.println(foundBook1);
                                                            System.out.println("Do you want to (1) Borrow or (2) Return this book or (3) Cancel?");
                                                            int action = scanner.nextInt();
                                                            scanner.nextLine(); // Consume the newline character
                                                            switch (action) {
                                                                case 1 -> {
                                                                    if (repository.getBookStock(foundBook1.getIsbn()) == 0) {
                                                                        foundBook1.isAvailable(false);
                                                                        System.out.println("The book is currently unavailable! Come back later!");
                                                                    } else {
                                                                        foundBook1.setBorrowDate();
                                                                        foundBook1.setDueDate();
                                                                        current.setBorrowedBook(foundBook1);
                                                                        repository.decrementBookStock(foundBook1.getIsbn());
                                                                        System.out.println("Borrow Date:" + foundBook1.getBorrowDate());
                                                                        System.out.println("Due Date:" + foundBook1.getDueDate());
                                                                        System.out.println("Book added to your list!");
                                                                    }
                                                                }
                                                                case 2 -> {
                                                                    current.setReturnedBooks(foundBook1);
                                                                    current.RemoveBorrowedBooks(foundBook1);
                                                                    repository.incrementBookStock(foundBook1.getIsbn());
                                                                    System.out.println("Book returned successfully!");
                                                                }
                                                                case 3 -> {
                                                                    System.out.println("Book cancelled.");
                                                                }
                                                                default -> System.out.println("Invalid action choice.");
                                                            }
                                                        } else {
                                                            System.out.println("No book found with the input given!");
                                                        }

                                                        assert foundBook1 != null;
                                                        System.out.println("\nAll books stock in the repository:");
                                                        allBookStock = ((InMemoryBookRepository) repository).getAllBookStock();
                                                        allBookStock.forEach((isbn, stock) -> {
                                                            System.out.println("ISBN: " + isbn + ", Stock: " + stock);
                                                        });
                                                    }
                                                    case 2 -> {
                                                        System.out.println("Enter book's ISBN :");
                                                        String isbn = scanner.nextLine();
                                                        Book foundBook2 = repository.searchBookISBN(isbn);
                                                        if (foundBook2 != null) {
                                                            System.out.println(foundBook2);
                                                            System.out.println("Do you want to (1) Borrow or (2) Return this book or (3) Cancel?");
                                                            int action = scanner.nextInt();
                                                            scanner.nextLine(); // Consume the newline character
                                                            switch (action) {
                                                                case 1 -> {
                                                                    if (repository.getBookStock(foundBook2.getIsbn()) == 0) {
                                                                        foundBook2.isAvailable(false);
                                                                        System.out.println("The book is currently unavailable! Come back later!");
                                                                    } else {
                                                                        foundBook2.setBorrowDate();
                                                                        foundBook2.setDueDate();
                                                                        current.setBorrowedBook(foundBook2);
                                                                        repository.decrementBookStock(foundBook2.getIsbn());
                                                                        System.out.println("Borrow Date:" + foundBook2.getBorrowDate());
                                                                        System.out.println("Due Date:" + foundBook2.getDueDate());
                                                                        System.out.println("Book added to your list!");
                                                                    }
                                                                }
                                                                case 2 -> {
                                                                    current.setReturnedBooks(foundBook2);
                                                                    current.RemoveBorrowedBooks(foundBook2);
                                                                    repository.incrementBookStock(foundBook2.getIsbn());

                                                                    System.out.println("Book returned successfully!");
                                                                }
                                                                default -> System.out.println("Invalid action choice.");
                                                            }
                                                        } else {
                                                            System.out.println("No book found with the input given!");
                                                        }

                                                        assert foundBook2 != null;
                                                        allBookStock = ((InMemoryBookRepository) repository).getAllBookStock();
                                                        allBookStock.forEach((isbn1, stock) -> {
                                                            System.out.println("ISBN: " + isbn1 + ", Stock: " + stock);
                                                        });
                                                    }
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input. Please enter a valid choice (1-3).");
                                            }
                                        }
                                        case 3 -> {
                                            val.LogOut(acc);
                                            count += 1;
                                            Start();
                                        }
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid choice (1-3).");
                                }
                            }
                            while (val.LogIn(username, password, acc));
                            break;
                        }
                        catch(FailedLoginException ex){
                            System.out.println("Please try again!");
                        }
                    }while (true);
                    case 3: {
                        System.out.println("Have a great day" + acc.getCurrentUser().getUsername() + "!");
                        System.exit(0);
                    }
                }
            }
            catch(InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid choice (1-3).");
            }
        }
        while (true);
    }
    public void Menu1(){
        System.out.println("""
                Welcome to the Library System!
                Please choose your options(1-3)
                1. Create new Account
                2. Log in
                3. Exit Application""");
        scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        scanner.nextLine();
    }

}
