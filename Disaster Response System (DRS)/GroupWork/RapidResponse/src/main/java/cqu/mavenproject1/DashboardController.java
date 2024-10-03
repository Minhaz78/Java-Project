package cqu.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class for the Dashboard
 *
 * @author asif
 */
public class DashboardController implements Initializable {

    @FXML
    private Button reportButton;  // Button for navigating to Disaster Report

    @FXML
    private Button logoutButton;   // Button for logging out and navigating to the Login screen

    @FXML
    private Button addresourceButton;  // Button for adding a resource, visible only to Resource Managers

    @FXML
    private Button updateprofileButton;  // Button for updating the user's profile

    @FXML
    private Button addalertbutton;  // Button for adding a disaster alert, visible only to Emergency Responders

    @FXML
    private TableView<Resource> resourceTable;  // TableView for displaying resources

    @FXML
    private TableColumn<Resource, String> resourceNameColumn;  // Column displaying resource names

    @FXML
    private TableColumn<Resource, Integer> availableUnitsColumn;  // Column displaying available resource units

    @FXML
    private TableColumn<Resource, Integer> totalUnitsColumn;  // Column displaying total resource units

    @FXML
    private TableColumn<Resource, Date> resourceDateColumn;  // Column displaying the date of resource update

    @FXML
    private TableView<Disaster> disasterTable;  // TableView for displaying disaster reports

    @FXML
    private TableColumn<Disaster, String> disasterTypeColumn;  // Column displaying types of disasters

    @FXML
    private TableColumn<Disaster, String> disasterLocationColumn;  // Column displaying disaster locations

    @FXML
    private TableColumn<Disaster, Date> disasterDateColumn;  // Column displaying the date of the disaster

    @FXML
    private TableColumn<Disaster, String> disasterDescriptionColumn;  // Column displaying descriptions of disasters

    @FXML
    private TableView<DisasterAlert> alertTableView;  // TableView for displaying disaster alerts

    @FXML
    private TableColumn<DisasterAlert, Date> alertDateColumn;  // Column displaying the date of the alert

    @FXML
    private TableColumn<DisasterAlert, String> alertTypeColumn;  // Column displaying the type of alert

    @FXML
    private TableColumn<DisasterAlert, Integer> alertSeverityColumn;  // Column displaying the severity of the alert

    @FXML
    private TableColumn<DisasterAlert, String> alertCriticalColumn;  // Column displaying whether the alert is critical

    @FXML
    private TableColumn<DisasterAlert, String> alertDescriptionColumn;  // Column displaying the description of the alert

    @FXML
    private TableView<LiveUpdate> liveUpdatesTable;  // TableView for displaying live updates

    @FXML
    private TableColumn<LiveUpdate, String> updateTypeColumn;  // Column displaying the type of live update

    @FXML
    private TableColumn<LiveUpdate, String> updateDescriptionColumn;  // Column displaying the description of the update

    @FXML
    private TableColumn<LiveUpdate, Timestamp> updateTimeColumn;  // Column displaying the time of the live update

    private final ObservableList<Resource> resourceData = FXCollections.observableArrayList();  // ObservableList for resource data

    private final ObservableList<Disaster> disasterData = FXCollections.observableArrayList();  // ObservableList for disaster data

    private final ObservableList<DisasterAlert> disasterAlertData = FXCollections.observableArrayList();  // ObservableList for disaster alerts

    private final ObservableList<LiveUpdate> liveUpdatesData = FXCollections.observableArrayList();  // ObservableList for live updates

    private static User loggedInUser;  // Reference to the currently logged-in user

    /**
     * Initializes the controller, setting up visibility and loading data. The
     * visibility of buttons like 'addresourceButton' and 'addalertbutton' is
     * set based on the user's role. The method also configures the table
     * columns and loads data from the database.
     *
     * @param url the location used to resolve relative paths for the root
     * object
     * @param rb the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addresourceButton.setVisible(false);
        addalertbutton.setVisible(false);

        if (loggedInUser != null) {
            String role = loggedInUser.getRole();

            if (role.equals("Resource Manager")) {
                addresourceButton.setVisible(true);  // Hide the "Add Resource" button for non-Resource Managers
            }
            if (role.equals("Emergency Responder")) {
                addalertbutton.setVisible(true);  // Hide the "Add Alert" button for non-Emergency Responders
            }
        }

        if (resourceTable != null) {
            resourceNameColumn.setCellValueFactory(new PropertyValueFactory<>("resourceName"));
            availableUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("availableUnits"));
            totalUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("totalUnits"));
            resourceDateColumn.setCellValueFactory(new PropertyValueFactory<>("date")); // Bind the date column

            resourceTable.setItems(resourceData);
            loadResourceDataFromDatabase();

        }

        // Initialize the Disaster Report Table
        if (disasterTable != null) {
            disasterTypeColumn.setCellValueFactory(new PropertyValueFactory<>("disasterType"));
            disasterLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            disasterDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));  // Bind the LocalDate column
            disasterDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            disasterTable.setItems(disasterData);  // Bind the ObservableList to the TableView
            loadDisasterDataFromDatabase();  // Load data from the database
        }

        // Existing initialization code for disaster and resource tables
        if (alertTableView != null) {
            alertDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            alertTypeColumn.setCellValueFactory(new PropertyValueFactory<>("alertType"));
            alertSeverityColumn.setCellValueFactory(new PropertyValueFactory<>("severity"));
            alertCriticalColumn.setCellValueFactory(new PropertyValueFactory<>("critical"));
            alertDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            alertTableView.setItems(disasterAlertData);  // Bind the ObservableList to the TableView
            loadAlertDataFromDatabase();  // Load data from the database
        }

        if (liveUpdatesTable != null) {
            // Setup Live Updates table columns
            updateTypeColumn.setCellValueFactory(new PropertyValueFactory<>("updateType"));
            updateDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            updateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

            loadLiveUpdatesFromDatabase();

            // Sort the live updates by time
            // liveUpdatesData.sort((u1, u2) -> u2.getTime().compareTo(u1.getTime()));  // Sort by time descending
        }

        reportButton.setOnAction(event -> {
            try {
                App.setRoot("disasterReport");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Attach listener to navigate to the Login view
        logoutButton.setOnAction(event -> {
            try {
                App.logoutUser();
                App.setRoot("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Attach listener to navigate to the Login view
        addresourceButton.setOnAction(event -> {
            try {
                App.setRoot("resource");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Attach listener to navigate to the Login view
        updateprofileButton.setOnAction(event -> {
            try {
                App.setRoot("updateprofile");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Attach listener to navigate to the Login view
        addalertbutton.setOnAction(event -> {
            try {
                App.setRoot("disasteralert");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Sets the logged-in user for the Dashboard.
     *
     * @param user the user who is logged in
     */
    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    /**
     * Loads disaster data from the database and populates the disaster table.
     */
    private void loadDisasterDataFromDatabase() {
        // Clear the existing disaster data
        disasterData.clear();

        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {
            String query = "SELECT * FROM disaster_reports";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String disasterType = rs.getString("disaster_type");
                String location = rs.getString("location");
                Date date = rs.getDate("date");
                String description = rs.getString("description");

                // Add the data to the ObservableList for the TableView
                disasterData.add(new Disaster(disasterType, location, date.toLocalDate(), description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadResourceDataFromDatabase() {
        // Clear the existing data
        resourceData.clear();

        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {
            String query = "SELECT * FROM resources";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String resourceName = rs.getString("resource_name");
                int availableUnits = rs.getInt("available_units");
                int totalUnits = rs.getInt("total_units");
                Date date = rs.getDate("date");
                resourceData.add(new Resource(resourceName, availableUnits, totalUnits, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAlertDataFromDatabase() {
        // Clear the existing alert data
        disasterAlertData.clear();

        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {
            String query = "SELECT * FROM alerts";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String alertType = rs.getString("alert_type");
                int severity = rs.getInt("severity");
                String critical = rs.getString("critical");
                String description = rs.getString("description");
                Date date = rs.getDate("date");

                disasterAlertData.add(new DisasterAlert(date, alertType, severity, critical, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to load live updates from the database
    private void loadLiveUpdatesFromDatabase() {
        liveUpdatesData.clear();  // Clear existing data

        String query = "SELECT * FROM live_updates ORDER BY timestamp DESC";

        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String updateType = rs.getString("update_type");
                String description = rs.getString("description");
                Timestamp timestamp = rs.getTimestamp("timestamp");

                // Add data to the ObservableList for the TableView
                liveUpdatesData.add(new LiveUpdate(updateType, description, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set the data to the TableView
        liveUpdatesTable.setItems(liveUpdatesData);  // Bind the ObservableList to the TableView
    }

}
