package src;

public class DebitPayment implements PaymentState {
    @Override
    public void payLateFees(double amount) {
        System.out.println("Late fees paid by debit: $" + amount);
    }
    public String getPaymentMethod(){
        return "Debit";
    }
}