package furniture;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private String productName;
    private int quantity;
    private double price;
    private String remarks;

    private static List<Purchase> purchases = new ArrayList<>();

    public Purchase(String productName, int quantity, double price, String remarks) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.remarks = remarks;
    }

    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getRemarks() { return remarks; }

    public static void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }
    public static List<Purchase> getPurchases() {
        return purchases;
    }
    public static void clearPurchases() {
        purchases.clear();
    }
}