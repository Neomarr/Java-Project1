package furniture; // Add this line only if your file is in the 'furniture' package.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseConnection {
    // Update these credentials with your MySQL details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/air_supplies"; // Replace with your database name
    private static final String DB_USER = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = "Jovelicious!091996"; // Replace with your MySQL password

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Try to establish a connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            if (connection != null) {
                System.out.println("Connection to the database was successful!");
            }
        } catch (SQLException e) {
            // Print any errors that occur during the connection attempt
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        } finally {
            // Close the connection if it was established
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}