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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * Controller class for handling disaster alert creation and submission in the
 * disaster alert view. This class initializes input fields, handles form
 * validation, and manages interactions with the database.
 * @author asif
 */
public class DisasterAlertController implements Initializable {

    @FXML
    private Button alertsubmitButton;  // Button for submitting the alert

    @FXML
    private Button alertgotodashboardButton;  // Button for navigating to the dashboard

    @FXML
    private Button logoutButton;  // Button for logging out of the application

    @FXML
    private ChoiceBox<String> alertTypeChoiceBox;  // ChoiceBox for selecting the type of alert

    @FXML
    private Spinner<Integer> alertseveritySpinner;  // Spinner for selecting the severity level of the alert

    @FXML
    private DatePicker alertdatePicker;  // DatePicker for selecting the date of the alert

    @FXML
    private ChoiceBox<String> alertcriticalChoiceBox;  // ChoiceBox for selecting the criticality level of the alert

    @FXML
    private TextField alertdescriptionTextField;  // TextField for entering a description of the alert

    /**
     * Initializes the disaster alert view and sets up input controls such as
     * choice boxes, spinners, and buttons.
     *
     * @param url the location used to resolve relative paths for the root
     * object
     * @param rb the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the alert type choices
        alertTypeChoiceBox.getItems().addAll("Fire", "Flood", "Earthquake", "Hurricane", "BushFire", "Other");

        // Initialize the criticality choices
        alertcriticalChoiceBox.getItems().addAll("High", "Medium", "Low");

        // Initialize the severity spinner with values from 1 to 5
        alertseveritySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1));

        // Handle "Submit" button action
        alertsubmitButton.setOnAction(event -> handleSubmit());

        // Handle "Go to Dashboard" button action
        alertgotodashboardButton.setOnAction(event -> {
            try {
                App.setRoot("dashboard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Handle "Log Out" button action
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
     * Handles the submission of the disaster alert form. Validates the input
     * fields and saves the alert data to the database.
     */
    private void handleSubmit() {
        String alertType = alertTypeChoiceBox.getValue();
        int severity = alertseveritySpinner.getValue();
        String critical = alertcriticalChoiceBox.getValue();
        String date = (alertdatePicker.getValue() != null) ? alertdatePicker.getValue().toString() : "No date selected";
        String description = alertdescriptionTextField.getText();

        // Simple validation to ensure all fields are filled
        if (alertType == null || critical == null || description.isEmpty()) {
            showAlert(AlertType.ERROR, "Form Error!", "Please fill in all fields.");
        } else {
            // Call the method to save alert data to the database
            saveAlertToDatabase(alertType, severity, critical, date, description);
        }
    }

    /**
     * Saves the disaster alert data to the in-memory database.
     *
     * @param alertType the type of the alert
     * @param severity the severity of the alert
     * @param critical the criticality of the alert
     * @param date the date of the alert
     * @param description the description of the alert
     */
    private void saveAlertToDatabase(String alertType, int severity, String critical, String date, String description) {
        String insertSQL = "INSERT INTO alerts (alert_type, severity, critical, date, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, alertType);
            pstmt.setInt(2, severity);
            pstmt.setString(3, critical);
            pstmt.setDate(4, Date.valueOf(date));  // Convert LocalDate to SQL Date
            pstmt.setString(5, description);

            pstmt.executeUpdate();  // Execute the insertion
            System.out.println("Alert saved successfully!");

            addLiveUpdateToDatabase("Alert", "New alert '" + alertType + " and critical level '" + critical + "' created.");
            App.setRoot("dashboard");  // Navigate to the dashboard upon successful submission

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a live update to the database to record the creation of the new
     * alert.
     *
     * @param updateType the type of the update (e.g., "Alert")
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
     * Displays an alert dialog to the user.
     *
     * @param alertType the type of the alert (e.g., ERROR, INFORMATION)
     * @param title the title of the alert dialog
     * @param message the message to be displayed in the alert dialog
     */
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.getDialogPane().setStyle(
                "-fx-font-family: 'Serif'; "
                + // Set the font to Serif
                "-fx-font-size: 14px;" // Set a custom font size if needed
        );
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
