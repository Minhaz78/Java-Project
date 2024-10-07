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

//     private static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
//     private static final String USER = "sa";
//     private static final String PASSWORD = "password";
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

    /**
     * Initializes the database by creating tables and inserting initial data.
     * This method creates tables such as 'users', 'disaster_reports',
     * 'resources', 'alerts', and 'live_updates', and populates them with
     * initial data entries for testing purposes.
     */
//     public static void initializeDatabase() {
//         String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
//                 + "id INT AUTO_INCREMENT PRIMARY KEY, "
//                 + "username VARCHAR(255) UNIQUE NOT NULL, "
//                 + "email VARCHAR(255) NOT NULL, "
//                 + "password VARCHAR(255) NOT NULL, "
//                 + "gender VARCHAR(50), "
//                 + "birthday DATE, "
//                 + "role VARCHAR(50) NOT NULL"
//                 + ")";
//         // SQL for creating the disaster_reports table
//         String createDisasterReportsTableSQL = "CREATE TABLE IF NOT EXISTS disaster_reports ("
//                 + "id INT AUTO_INCREMENT PRIMARY KEY, "
//                 + "disaster_type VARCHAR(100), "
//                 + "location VARCHAR(255), "
//                 + "date DATE, "
//                 + "description TEXT"
//                 + ");";
//         // Add a new table for resources
//         String createResourcesTableSQL = "CREATE TABLE IF NOT EXISTS resources ("
//                 + "id INT AUTO_INCREMENT PRIMARY KEY, "
//                 + "resource_name VARCHAR(255) NOT NULL, "
//                 + "available_units INT NOT NULL, "
//                 + "total_units INT NOT NULL, "
//                 + "date DATE NOT NULL"
//                 + ");";
//         // SQL for creating the alerts table
//         String createAlertsTableSQL = "CREATE TABLE IF NOT EXISTS alerts ("
//                 + "id INT AUTO_INCREMENT PRIMARY KEY, "
//                 + "alert_type VARCHAR(100), "
//                 + "severity INT NOT NULL, "
//                 + "critical VARCHAR(10) NOT NULL, "
//                 + "description TEXT, "
//                 + "date DATE NOT NULL"
//                 + ");";
//         String createLiveUpdatesTableSQL = "CREATE TABLE IF NOT EXISTS live_updates ("
//                 + "id INT AUTO_INCREMENT PRIMARY KEY, "
//                 + "update_type VARCHAR(50) NOT NULL, "
//                 + "description TEXT NOT NULL, "
//                 + "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
//                 + ");";
//         String insertUserSQL = "INSERT INTO users (username, email, password, gender, birthday, role) VALUES "
//                 + "('visitor', 'visitor@example.com', 'visitor', 'Male', '1990-05-10', 'Visitor'), "
//                 + "('manager', 'manager@example.com', 'manager', 'Female', '1985-02-15', 'Resource Manager'), "
//                 + "('responder', 'responder@example.com', 'responder', 'Other', '1980-11-20', 'Emergency Responder');";
//         // SQL to insert initial resource data
//         String water_resource = "INSERT INTO resources (resource_name, available_units, total_units, date) VALUES ('Water Supply', 100, 200, '2024-09-01')";
//         String medical_resource = "INSERT INTO resources (resource_name, available_units, total_units, date) VALUES ('Medical Kits', 50, 100, '2024-09-02')";
//         String food_resource = "INSERT INTO resources (resource_name, available_units, total_units, date) VALUES ('Food Supplies', 200, 300, '2024-09-03')";
//         String tents_resource = "INSERT INTO resources (resource_name, available_units, total_units, date) VALUES ('Emergency Tents', 20, 50, '2024-09-04')";
//         // SQL to insert initial disaster report data
//         String fire_disaster = "INSERT INTO disaster_reports (disaster_type, location, date, description) VALUES ('Fire', 'California', '2024-08-15', 'A massive wildfire spreading rapidly across California.')";
//         String flood_disaster = "INSERT INTO disaster_reports (disaster_type, location, date, description) VALUES ('Flood', 'Queensland', '2024-09-01', 'Severe flooding caused by heavy rains in Queensland.')";
//         String earthquake_disaster = "INSERT INTO disaster_reports (disaster_type, location, date, description) VALUES ('Earthquake', 'Tokyo', '2024-09-07', 'A major earthquake with a magnitude of 7.2 hit Tokyo.')";
//         String hurricane_disaster = "INSERT INTO disaster_reports (disaster_type, location, date, description) VALUES ('Hurricane', 'Florida', '2024-09-12', 'A Category 5 hurricane approaching the coast of Florida.')";
//         // Insert initial alert data
//         String alert1 = "INSERT INTO alerts (alert_type, severity, critical, description, date) VALUES ('Fire', 4, 'High', 'Fire reported in the northern region.', '2024-08-10');";
//         String alert2 = "INSERT INTO alerts (alert_type, severity, critical, description, date) VALUES ('Flood', 3, 'Medium', 'Minor flooding in the eastern district.', '2024-08-15');";
//         String alert3 = "INSERT INTO alerts (alert_type, severity, critical, description, date) VALUES ('Earthquake', 5, 'High', 'Strong earthquake hit downtown area.', '2024-08-20');";
//         String alert4 = "INSERT INTO alerts (alert_type, severity, critical, description, date) VALUES ('Hurricane', 4, 'Low', 'Hurricane approaching coastal city.', '2024-08-25');";
//         // Insert Live Update data
//         String insertLiveUpdatesSQL = "INSERT INTO live_updates (update_type, description) VALUES "
//                 + "('Resource', 'New resource Water Supply created with 100 available units.'), "
//                 + "('Disaster', 'A new disaster report of a flood in Queensland.'), "
//                 + "('Alert', 'Severe weather alert issued for hurricane approaching Florida.'), "
//                 + "('Resource', 'New resource Medical Kits created with 50 available units.'), "
//                 + "('Disaster', 'Earthquake with magnitude 7.2 hit Tokyo, Japan.'), "
//                 + "('Alert', 'New alert issued: Wildfire detected in California.'), "
//                 + "('Resource', 'Food Supplies created with 200 units for emergency.'), "
//                 + "('Alert', 'Critical alert issued for high-risk area in Florida due to hurricane.'), "
//                 + "('Disaster', 'New fire reported in California spreading rapidly.');";
//         // Execute the SQL queries to create tables and insert data
//         try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
//             stmt.execute(createTableSQL);
//             stmt.execute(insertUserSQL);
//             // create resource table
//             stmt.execute(createResourcesTableSQL);
//             stmt.execute(water_resource);
//             stmt.execute(medical_resource);
//             stmt.execute(food_resource);
//             stmt.execute(tents_resource);
//             // create disaster resource table
//             stmt.execute(createDisasterReportsTableSQL);
//             stmt.execute(fire_disaster);
//             stmt.execute(flood_disaster);
//             stmt.execute(earthquake_disaster);
//             stmt.execute(hurricane_disaster);
//             stmt.execute(createAlertsTableSQL);
//             // Insert initial alerts data
//             stmt.execute(alert1);
//             stmt.execute(alert2);
//             stmt.execute(alert3);
//             stmt.execute(alert4);
//             stmt.execute(createLiveUpdatesTableSQL);
//             stmt.execute(insertLiveUpdatesSQL);
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
}
