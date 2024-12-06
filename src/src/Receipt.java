package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Receipt {

    public static void PrintReceipt(Scanner scanner, Account user, double amount){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        System.out.println("Would you like to print a receipt? (Y/N)");
        String ans = scanner.nextLine();
        if (ans.equalsIgnoreCase("Y")){
            String resultString = String.format("\n\n\t\tLibrary System:\t%s\n\n\t\tUser:   \t%s\n\t\tPayment Method:\t%s\n\t\tAmount Paid:\t%f\n\t\t     Thanks for coming by!"
                    ,formattedDate,user.getUsername(),user.getPaymentState(),amount);
            System.out.println(resultString);
        }
    }

}
