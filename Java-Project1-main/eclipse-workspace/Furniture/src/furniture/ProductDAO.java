package furniture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Product operations
 * Handles database operations related to products
 */
public class ProductDAO {
    
    /**
     * Get all products from the database
     * @return List of Product objects
     */
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        
        String sql = "SELECT product_id, product_name, description, price, stock, image_path FROM products";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("image_path")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products from the database!");
            e.printStackTrace();
        }
        
        return products;
    }
    
    /**
     * Update inventory quantity for a product
     * @param productId The product ID
     * @param newQuantity The new quantity in stock
     * @return true if the update was successful, false otherwise
     */
    public static boolean updateInventory(int productId, int newQuantity) {
        String sql = "UPDATE products SET stock = ? WHERE product_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, productId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating inventory in the database!");
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Find a product by name
     * @param productName The name of the product to find
     * @return Product object if found, null otherwise
     */
    public static Product findProductByName(String productName) {
        String sql = "SELECT product_id, product_name, description, price, stock, image_path FROM products WHERE product_name = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, productName);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock"),
                            rs.getString("image_path")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding product by name in the database!");
            e.printStackTrace();
        }
        
        return null;
    }
}