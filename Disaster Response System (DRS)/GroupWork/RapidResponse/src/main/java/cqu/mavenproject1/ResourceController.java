package cqu.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Controller class for handling resource submission in the resource view. This
 * class initializes input fields, validates form data, saves the resource
 * information to the database, and interacts with other views like the
 * dashboard and login.
 *
 * @author asif
 */
public class ResourceController implements Initializable {

    @FXML
    private Button submitButton1;  // Button for submitting the resource

    @FXML
    private Button gotodashboardButton;  // Button for navigating back to the dashboard

    @FXML
    private Button logoutButton;  // Button for logging out of the application

    @FXML
    private TextField resourceNameField;  // TextField for entering the resource name

    @FXML
    private TextField availableUnitsField;  // TextField for entering the available units

    @FXML
    private TextField totalUnitsField;  // TextField for entering the total units

    @FXML
    private DatePicker datePicker;  // DatePicker for selecting the date when the resource was added

    private DashboardController dashboardController;  // Reference to DashboardController (unused here)

    /**
     * Initializes the resource view and sets up input controls like buttons and
     * text fields. It also handles navigation and form submissions.
     *
     * @param url the location used to resolve relative paths for the root
     * object
     * @param rb the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the styles for buttons
        submitButton1.setStyle("-fx-font-family: 'serif';");
        gotodashboardButton.setStyle("-fx-font-family: 'serif';");

        // Handle Submit button click
        submitButton1.setOnAction(event -> {
            handleSubmit();
        });

        gotodashboardButton.setOnAction(event -> {
            try {
                App.setRoot("dashboard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logoutButton.setOnAction(event -> {
            try {
                App.logoutUser();
                App.setRoot("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Handles the submission of resource data. Validates the form fields and
     * saves the data to the in-memory database.
     */
    private void handleSubmit() {
        String resourceName = resourceNameField.getText();
        String availableUnitsString = availableUnitsField.getText();
        String totalUnitsString = totalUnitsField.getText();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "No date selected";

        // Validate input fields
        if (availableUnitsString.isBlank() || totalUnitsString.isBlank()) {
            showAlert("Form input error", "Please check the fields.");
            return;  // Stop further execution if fields are empty
        } else {
            showAlert("Form input", "Resource has been successfully submitted.");
            saveResourceToDatabase(resourceName, availableUnitsString, totalUnitsString, date);
        }
    }

    /**
     * Saves the resource data to the database.
     *
     * @param resourceName the name of the resource
     * @param availableUnitsString the number of available units
     * @param totalUnitsString the total number of units
     * @param date the date the resource was added
     */
    private void saveResourceToDatabase(String resourceName, String availableUnitsString, String totalUnitsString, String date) {
        try {
            int availableUnits = Integer.parseInt(availableUnitsString);
            int totalUnits = Integer.parseInt(totalUnitsField.getText());

            String insertSQL = "INSERT INTO resources (resource_name, available_units, total_units, date) VALUES (?, ?, ?, ?)";

            try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                pstmt.setString(1, resourceName);
                pstmt.setInt(2, availableUnits);
                pstmt.setInt(3, totalUnits);
                pstmt.setDate(4, Date.valueOf(date));  // Convert LocalDate to SQL Date

                pstmt.executeUpdate();
                System.out.println("Resource saved successfully!");
                addLiveUpdateToDatabase("Resource", "New resource '" + resourceName + "' created with " + availableUnits + " available units.");
                App.setRoot("dashboard");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a live update to the database to record the creation of a new
     * resource.
     *
     * @param updateType the type of update (e.g., "Resource")
     * @param description a description of the update
     */
    private void addLiveUpdateToDatabase(String updateType, String description) {
        String insertLiveUpdateSQL = "INSERT INTO live_updates (update_type, description) VALUES (?, ?)";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(insertLiveUpdateSQL)) {

            pstmt.setString(1, updateType);
            pstmt.setString(2, description);

            pstmt.executeUpdate();
            System.out.println("Live update added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays an alert message to the user.
     *
     * @param title the title of the alert
     * @param message the message to be displayed in the alert
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.getDialogPane().setStyle(
                "-fx-font-family: 'Serif'; "
                + // Set the font to Serif
                "-fx-font-size: 14px;" // Set a custom font size if needed
        );
        alert.setTitle(title);
        alert.setHeaderText(null);  // No header
        alert.setContentText(message);
        alert.showAndWait();
    }
}
