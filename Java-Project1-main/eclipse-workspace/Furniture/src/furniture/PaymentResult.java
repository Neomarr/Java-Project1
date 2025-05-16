package furniture;

public class PaymentResult {
    public String paymentMethod;
    public String paymentDetails;
    public PaymentResult(String method, String details) {
        this.paymentMethod = method;
        this.paymentDetails = details;
    }
}
