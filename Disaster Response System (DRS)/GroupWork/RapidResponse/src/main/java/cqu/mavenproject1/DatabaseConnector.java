package cqu.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing the database connection and initializing the
 * database.
 *
 * @author asif
 */
public class DatabaseConnector {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/drs_database"; // MySQL URL with database name
    private static final String USER = "root";  // MySQL username
    private static final String PASSWORD = "password";  // MySQL password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure MySQL JDBC Driver is loaded
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    /**
     * Establishes a connection to the database.
     *
     * @return a Connection object to interact with the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}
