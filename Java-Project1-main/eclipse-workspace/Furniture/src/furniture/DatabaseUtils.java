package furniture;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/air_supplies"; // Replace with your database name
    private static final String DB_USER = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = "Jovelicious!091996"; // Replace with your MySQL password

    // Method to establish a connection to the database
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connection successful!");
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }
} 