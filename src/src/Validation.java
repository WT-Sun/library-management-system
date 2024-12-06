package src;
import java.util.*;
public class Validation {
    // validate login credentials

    public boolean LogIn(String username, String password, AccDB a) {
        for (Account user : a.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                a.setCurrentUser(user);
                return true;
            }
        }
        System.out.println("Username does not exist or incorrect password.");
        return false;
    }


    // log out a user
    public void LogOut(AccDB currentUser) {
        if (currentUser != null) {
            System.out.println("Logged out successfully!");
            currentUser.setCurrentUser(null);

        }
    }

}
