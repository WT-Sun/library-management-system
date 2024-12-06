package src;

import java.util.ArrayList;
import java.util.Scanner;

class AccDB {
    private Account currentUser;
    final ArrayList<Account> users;

    public AccDB() {
        users = new ArrayList<>();
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Account user) {
        this.currentUser = user;
    }

    // register a new account
    public void registerAcc(Scanner scanner) {
        System.out.println("Enter a username (enter 'cancel' to cancel): ");
        String username = scanner.nextLine();

        // check if the user wants to cancel registration
        if (username.equals("cancel")) {
            System.out.println("Registration canceled.");
            return;
        }

        // check if the username is already taken
        if (isUsernameTaken(username)) {
            System.out.println("Username already taken. Please choose another.");
        } else {
            // if the username is available, prompt for a password and create a new user
            System.out.println("Enter a password: ");
            String password = scanner.nextLine();
            Account newUser = new Account(username, password);
            users.add(newUser);
            System.out.println("Registration successful for user: " + username);
        }
    }

    // check if a username is already taken
    private boolean isUsernameTaken(String username) {
        for (Account user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
