package cqu.drs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller class for managing disaster reports in the Disaster Response System (DRS).
 * It allows users to report disasters, cancel reports, and log out of the system.
 * 
 * Author: Mohammad Minhaz Uddin
 */
public class UserController {

    private Main mainApp;
    private User loggedInUser;  // Store the currently logged-in user

    @FXML
    private Label usernameLabel;  // Label to display the logged-in username

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField locationField;

    @FXML
    private Spinner<Integer> severitySpinner;

    @FXML
    private ComboBox<String> criticalComboBox;

    @FXML
    private TableView<Disaster> disasterTableView;

    @FXML
    private TableColumn<Disaster, String> typeColumn;

    @FXML
    private TableColumn<Disaster, String> descriptionColumn;

    @FXML
    private TableColumn<Disaster, String> locationColumn;

    @FXML
    private TableColumn<Disaster, Integer> severityColumn;

    @FXML
    private TableColumn<Disaster, Boolean> criticalColumn;

    @FXML
    private void initialize() {
        // Populate ComboBox and Spinner with default values
        typeComboBox.setItems(FXCollections.observableArrayList("Earthquake", "Flood", "Fire", "Tornado", "Hurricane"));
        criticalComboBox.setItems(FXCollections.observableArrayList("Yes", "No"));
        severitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5));

        // Set up table columns
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        severityColumn.setCellValueFactory(cellData -> cellData.getValue().severityProperty().asObject());
        criticalColumn.setCellValueFactory(cellData -> cellData.getValue().criticalProperty().asObject());

        // Load disaster data into the table
        refreshDisasterList();
    }

    /**
     * Sets the reference to the main application.
     * @param mainApp The main application instance.
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Displays the logged-in user's username.
     * @param user The user currently logged in.
     */
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        usernameLabel.setText("Logged in as: " + user.getUsername());
    }

    /**
     * Reports a disaster after verifying all fields are filled in correctly.
     */
    @FXML
    private void reportDisaster() {
        String type = typeComboBox.getValue();
        String description = descriptionField.getText();
        String location = locationField.getText();
        Integer severity = severitySpinner.getValue();
        boolean critical = "Yes".equals(criticalComboBox.getValue());

        if (type == null || description.isEmpty() || location.isEmpty() || severity == null) {
            showAlert(AlertType.ERROR, "Incomplete Data", "Please fill in all fields before submitting.");
        } else {
            boolean success = DBConnection.saveDisaster(type, description, location, severity, critical);
            if (success) {
                showAlert(AlertType.INFORMATION, "Success", "Disaster reported successfully!");
                refreshDisasterList();
            } else {
                showAlert(AlertType.ERROR, "Error", "Failed to report disaster.");
            }
        }
    }

    /**
     * Clears the disaster report form when the cancel button is pressed.
     */
    @FXML
    private void handleCancel() {
        typeComboBox.setValue(null);
        descriptionField.clear();
        locationField.clear();
        severitySpinner.getValueFactory().setValue(5);  // Reset to default value
        criticalComboBox.setValue(null);
    }

    /**
     * Handles user logout and navigates back to the login page.
     */
    @FXML
    private void handleLogout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You have successfully logged out.");
        alert.showAndWait();
        mainApp.showLoginPage();
    }

    /**
     * Refreshes the disaster table with updated disaster data.
     */
    private void refreshDisasterList() {
        ObservableList<Disaster> disasters = FXCollections.observableArrayList(DBConnection.getDisasters());
        disasterTableView.setItems(disasters);
    }

    /**
     * Displays an alert dialog with a specified message.
     * @param alertType The type of the alert (e.g., INFORMATION, ERROR).
     * @param title The title of the alert dialog.
     * @param content The content message to display in the alert.
     */
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
