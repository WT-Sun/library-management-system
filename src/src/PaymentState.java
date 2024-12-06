package src;

public interface PaymentState {
    void payLateFees(double amount);
    String getPaymentMethod();
}
