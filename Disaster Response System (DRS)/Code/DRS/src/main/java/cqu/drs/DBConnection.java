package cqu.drs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides database connection and operations for the Disaster Response System.
 * Handles user registration, login, disaster reporting, and fetching disaster data.
 * 
 * @author Mohammad Minhaz Uddin
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/drs";  // Database URL
    private static final String USER = "root";  // Your MySQL username
    private static final String PASSWORD = "password";  // Your MySQL password

    /**
     * Establishes a connection to the database.
     * 
     * @return Connection object for interacting with the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Registers a new user in the database.
     * 
     * @param username the user's chosen username
     * @param password the user's chosen password
     * @param role the user's role (e.g., "User" or "Admin")
     * @return true if registration is successful, false otherwise
     */
    public static boolean registerUser(String username, String password, String role) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            return stmt.executeUpdate() > 0;  // Returns true if the insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Returns false if an error occurred
        }
    }

    /**
     * Logs in a user by checking credentials in the database.
     * 
     * @param username the entered username
     * @param password the entered password
     * @return User object if login is successful, null otherwise
     */
    public static User login(String username, String password) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }

            sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"), "Admin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Saves a disaster report in the database.
     * 
     * @param type the type of disaster (e.g., "Fire", "Flood")
     * @param description a brief description of the disaster
     * @param location the location of the disaster
     * @param severity the severity level of the disaster (1-10)
     * @param critical whether the disaster is critical (true/false)
     * @return true if the report is saved successfully, false otherwise
     */
    public static boolean saveDisaster(String type, String description, String location, int severity, boolean critical) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO disasters (type, description, location, severity, critical) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setInt(4, severity);
            stmt.setBoolean(5, critical);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all disaster reports from the database.
     * 
     * @return a list of Disaster objects containing details of all reported disasters
     */
    public static List<Disaster> getDisasters() {
        List<Disaster> disasters = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM disasters";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Disaster disaster = new Disaster(
                    rs.getString("type"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getInt("severity"),
                    rs.getBoolean("critical")
                );
                disasters.add(disaster);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disasters;
    }
}
