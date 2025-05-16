package furniture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Connection Manager for Air Supplies Inventory System
 * Handles connections to the MySQL database
 */
public class DatabaseConnection {
    // JDBC URL, username and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/air_supplies";
    private static final String USER = "root";
    private static final String PASSWORD = "Jovelicious!091996"; // Enter your MySQL password here
    
    // JDBC variables for opening, closing and managing connection
    private static Connection connection;
    
    /**
     * Get a connection to the database
     * @return Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Open a connection
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            System.err.println("Connection to database failed!");
            e.printStackTrace();
            throw e;
        }
        return connection;
    }
    
    /**
     * Close the database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection!");
            e.printStackTrace();
        }
    }
    /**
     * Test the database connection
     * @return true if connection is successful, false otherwise
     */
    public static boolean testConnection() {
        try {
            getConnection();
            return true;
        } catch (SQLException e) {
            System.err.println("Database connection test failed!");
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }
}