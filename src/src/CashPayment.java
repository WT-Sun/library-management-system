package src;

public class CashPayment implements PaymentState {
    @Override
    public void payLateFees(double amount) {
        System.out.println("Late fees paid in cash: $" + amount);
        // should user have a wallet do deduct from ?
    }
    public String getPaymentMethod(){
        return "Cash";
    }
}