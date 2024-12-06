package src;

public class CreditPayment implements PaymentState {
    @Override
    public void payLateFees(double amount) {
        System.out.println("Late fees paid by credit: $" + amount);
    }
    public String getPaymentMethod(){
        return "Credit";
    }
}