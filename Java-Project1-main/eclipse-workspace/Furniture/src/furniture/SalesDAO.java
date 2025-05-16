package furniture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Data Access Object for Sales operations
 * Handles database operations related to sales and purchases
 */
public class SalesDAO {
    
    /**
     * Process a complete sale transaction
     * @param customerName Customer name
     * @param contactNumber Customer contact number
     * @param address Customer address
     * @param purchases List of purchases
     * @return The sale ID if successful, -1 otherwise
     */
    public static int processSale(String customerName, String contactNumber, String address, List<Purchase> purchases) {
        Connection conn = null;
        int saleId = -1;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            
            // Step 1: Insert customer details
            int customerId = insertCustomer(conn, customerName, contactNumber, address);
            
            // Step 2: Insert sale record
            double totalAmount = purchases.stream().mapToDouble(p -> p.price * p.quantity).sum();
            saleId = insertSale(conn, customerId, totalAmount);
            
            // Step 3: Insert sale items and update inventory
            for (Purchase purchase : purchases) {
                Product product = ProductDAO.findProductByName(purchase.title);
                if (product != null) {
                    insertSaleItem(conn, saleId, product.getId(), purchase.quantity, purchase.price);
                    ProductDAO.updateInventory(product.getId(), product.getStock() - purchase.quantity);
                }
            }
            
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Error processing sale in the database!");
            e.printStackTrace();
            
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return -1;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return saleId;
    }
    
    private static int insertCustomer(Connection conn, String name, String contactNumber, String address) throws SQLException {
        String sql = "INSERT INTO customers (name, contact_number, address) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, contactNumber);
            stmt.setString(3, address);
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        
        return -1;
    }
    
    private static int insertSale(Connection conn, int customerId, double totalAmount) throws SQLException {
        String sql = "INSERT INTO sales (customer_id, total_amount) VALUES (?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, customerId);
            stmt.setDouble(2, totalAmount);
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        
        return -1;
    }
    
    private static void insertSaleItem(Connection conn, int saleId, int productId, int quantity, double price) throws SQLException {
        String sql = "INSERT INTO sale_items (sale_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, saleId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            stmt.executeUpdate();
        }
    }
}