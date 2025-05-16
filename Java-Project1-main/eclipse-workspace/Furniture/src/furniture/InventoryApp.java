package furniture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InventoryApp {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Jovelicious!091996";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Query to fetch product information with inventory
            String query = "SELECT p.product_name, p.description, c.category_name, i.quantity_in_stock, p.price " +
                           "FROM products p " +
                           "JOIN categories c ON p.category_id = c.category_id " +
                           "JOIN inventory i ON p.product_id = i.product_id";

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            // Print the inventory data
            System.out.println("Inventory:");
            while (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                String description = resultSet.getString("description");
                String categoryName = resultSet.getString("category_name");
                int stock = resultSet.getInt("quantity_in_stock");
                double price = resultSet.getDouble("price");

                System.out.printf("Product: %s | Category: %s | Stock: %d | Price: %.2f\n", 
                                  productName, categoryName, stock, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
