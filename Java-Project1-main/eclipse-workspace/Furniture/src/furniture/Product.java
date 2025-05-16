package furniture;

public class Product {
    private String name;
    private String description;
    private int stock;

    public Product(String name, String description, int stock) {
        this.name = name;
        this.description = description;
        this.stock = stock;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}