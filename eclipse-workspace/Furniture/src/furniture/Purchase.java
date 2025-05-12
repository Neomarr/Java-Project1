package furniture;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    public String title;
    public int quantity;
    public double price;
    public String discountType;

    private static List<Purchase> purchaseList = new ArrayList<>();

    public Purchase(String title, int quantity, double price, String discountType) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.discountType = discountType;
    }

    public static void addPurchase(Purchase p) {
        purchaseList.add(p);
    }

    public static List<Purchase> getPurchases() {
        return purchaseList;
    }

    public static void clearPurchases() {
        purchaseList.clear();
    }
}