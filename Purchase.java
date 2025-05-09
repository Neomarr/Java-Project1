package furniture;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    public String title;
    public int quantity;
    public double price;
    public String discountType;

    // Static list to store purchases
    private static List<Purchase> purchases = new ArrayList<>();

    public Purchase(String title, int quantity, double price, String discountType) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.discountType = discountType;
    }

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
