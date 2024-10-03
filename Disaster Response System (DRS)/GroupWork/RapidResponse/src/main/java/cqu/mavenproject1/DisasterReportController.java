package cqu.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for handling disaster report submissions in the disaster
 * report view. This class initializes input fields, validates form data,
 * handles the submission of disaster reports, and interacts with the database
 * to store disaster report data.
 *
 * @author asif
 */
public class DisasterReportController implements Initializable {

    @FXML
    private Button submitButton;  // Button for submitting the disaster report

    @FXML
    private ChoiceBox<String> disastertypeChoiceBox;  // ChoiceBox for selecting the type of disaster

    @FXML
    private TextField locationField;  // TextField for entering the location of the disaster

    @FXML
    private DatePicker dateField;  // DatePicker for selecting the date of the disaster

    @FXML
    private TextArea descriptionField;  // TextArea for entering a description of the disaster

    @FXML
    private Button DashboardButton2;  // Button for navigating to the dashboard

    @FXML
    private Button logoutButton;  // Button for logging out of the application

    /**
     * Initializes the disaster report view and sets up input controls like
     * choice boxes and buttons. Also populates the disaster type choices and
     * handles button actions for form submission, navigation, and logging out.
     *
     * @param url the location used to resolve relative paths for the root
     * object
     * @param rb the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DashboardButton2.setOnAction(event -> {
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

        // Populate disaster type choices
        disastertypeChoiceBox.getItems().addAll("Fire", "Flood", "Earthquake", "Hurricane", "BushFire", "Other");

        // Handle submit button action to save disaster report to the database
        submitButton.setOnAction(event -> handleDisasterReportSubmission());
    }

    /**
     * Handles the disaster report submission logic. Validates the input fields
     * and submits the report to the database.
     */
    private void handleDisasterReportSubmission() {
        String disasterTypeValue = disastertypeChoiceBox.getValue();
        String locationValue = locationField.getText();
        String dateValue = (dateField.getValue() != null) ? dateField.getValue().toString() : "Not selected";
        String descriptionValue = descriptionField.getText();

        try {
            if (disasterTypeValue == null || locationValue.isBlank() || descriptionValue.isBlank()) {
                showAlert("Submission Error", "Please fill in all fields.");
            } else {
                boolean isSubmissionSuccessful = submitDisasterReport(disasterTypeValue, locationValue, dateValue, descriptionValue);
                if (isSubmissionSuccessful) {
                    showAlert("Submission Successful", "Disaster report submitted successfully!");
                    addLiveUpdateToDatabase("Disaster", "New disaster '" + disasterTypeValue + "' happened at location: " + locationValue + ".");
                    App.setRoot("dashboard"); // Navigate to dashboard upon successful submission
                } else {
                    showAlert("Submission Failed", "There was an error submitting the disaster report.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Submits the disaster report data to the database.
     *
     * @param disasterType the type of the disaster
     * @param location the location where the disaster occurred
     * @param date the date of the disaster
     * @param description the description of the disaster
     * @return true if the submission is successful, false otherwise
     */
    private boolean submitDisasterReport(String disasterType, String location, String date, String description) {
        String insertQuery = "INSERT INTO disaster_reports (disaster_type, location, date, description) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, disasterType);
            stmt.setString(2, location);
            stmt.setString(3, date);  // Here we use java.sql.Date
            stmt.setString(4, description);
            stmt.executeUpdate();

            return true; // Submission successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Submission failed
        }
    }

    /**
     * Adds a live update to the database to record the new disaster report.
     *
     * @param updateType the type of update (e.g., "Disaster")
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
     * Displays an alert message to the user for success or error notifications.
     *
     * @param title the title of the alert
     * @param message the message to be displayed in the alert
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setStyle(
                "-fx-font-family: 'Serif'; "
                + // Set the font to Serif
                "-fx-font-size: 14px;" // Set a custom font size if needed
        );
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
