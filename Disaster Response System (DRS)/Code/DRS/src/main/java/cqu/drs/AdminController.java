package cqu.drs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller class for the Admin interface.
 * Handles the assessment of reported disasters and logging out.
 * 
 * @author Mohammad Minhaz Uddin
 */
public class AdminController {

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

    private Main mainApp;

    /**
     * Initializes the controller class and populates the table with disasters.
     * Connects the TableView columns with the Disaster model properties.
     */
    @FXML
    private void initialize() {
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        severityColumn.setCellValueFactory(cellData -> cellData.getValue().severityProperty().asObject());
        criticalColumn.setCellValueFactory(cellData -> cellData.getValue().criticalProperty().asObject());

        // Convert List to ObservableList and set data to the TableView
        ObservableList<Disaster> disasters = FXCollections.observableArrayList(DBConnection.getDisasters());
        disasterTableView.setItems(disasters);
    }

    /**
     * Handles the assessment of a selected disaster and informs the relevant department.
     * Displays a custom message based on the type of disaster.
     */
    @FXML
    private void handleAssessDisaster() {
        Disaster selectedDisaster = disasterTableView.getSelectionModel().getSelectedItem();
        if (selectedDisaster != null) {
            // Custom message based on the type of disaster
            String message;
            switch (selectedDisaster.getType().toLowerCase()) {
                case "fire":
                    message = "Disaster has been assessed and the Fire Department has been informed!";
                    break;
                case "flood":
                    message = "Disaster has been assessed and the Flood Management Department has been informed!";
                    break;
                case "earthquake":
                    message = "Disaster has been assessed and the Earthquake Response Team has been informed!";
                    break;
                case "hurricane":
                    message = "Disaster has been assessed and the Hurricane Response Department has been informed!";
                    break;
                default:
                    message = "Disaster has been assessed and the relevant departments have been informed!";
                    break;
            }

            // Print the message to the console
            System.out.println(message);

            // Show confirmation in a dialog box
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Assessment Complete");
            alert.setHeaderText("Disaster has been assessed!");
            alert.setContentText(message);  // Show the custom message in the alert dialog
            alert.showAndWait();
        } else {
            // Show warning if no disaster is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No disaster selected");
            alert.setContentText("Please select a disaster from the table to assess.");
            alert.showAndWait();
        }
    }

    /**
     * Handles the logout action and displays a confirmation message.
     * Navigates back to the login page.
     */
    @FXML
    private void handleLogout() {
        // Show a confirmation alert for successful logout
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You have successfully logged out.");
        alert.showAndWait();
        mainApp.showLoginPage();
    }

    /**
     * Sets the reference to the main application.
     * 
     * @param mainApp the main application object.
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
