package cqu.drs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * The controller responsible for managing the disaster reporting and assessment
 * process in the Disaster Response System (DRS).
 * 
 * This class allows users to report disasters and assess reported disasters.
 * It handles interaction between the GUI elements and the backend logic.
 * 
 * @author Mohammad Minhaz Uddin
 */
public class DisasterController {

    /** The user currently logged into the system */
    private User user;

    /** ComboBox for selecting the type of disaster */
    @FXML
    private ComboBox<String> typeComboBox;

    /** TextArea for entering a description of the disaster */
    @FXML
    private TextArea descriptionField;

    /** TextField for entering the location of the disaster */
    @FXML
    private TextField locationField;

    /** Spinner for selecting the severity of the disaster */
    @FXML
    private Spinner<Integer> severitySpinner;

    /** ComboBox for indicating if the disaster is critical */
    @FXML
    private ComboBox<Boolean> criticalComboBox;

    /** TableView for displaying reported disasters */
    @FXML
    private TableView<Disaster> disasterTableView;

    /** TableColumn for displaying the type of disaster */
    @FXML
    private TableColumn<Disaster, String> typeColumn;

    /** TableColumn for displaying the description of the disaster */
    @FXML
    private TableColumn<Disaster, String> descriptionColumn;

    /** TableColumn for displaying the location of the disaster */
    @FXML
    private TableColumn<Disaster, String> locationColumn;

    /** TableColumn for displaying the severity of the disaster */
    @FXML
    private TableColumn<Disaster, Integer> severityColumn;

    /** TableColumn for displaying whether the disaster is critical */
    @FXML
    private TableColumn<Disaster, Boolean> criticalColumn;

    /**
     * Sets the logged-in user for the controller.
     * 
     * @param user The user who is currently logged in
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Initializes the controller, setting up combo boxes and table columns.
     * Called automatically after the FXML elements have been loaded.
     */
    @FXML
    public void initialize() {
        // Initialize the combo boxes and table columns
        typeComboBox.getItems().addAll("Fire", "Flood", "Earthquake", "Hurricane");
        criticalComboBox.getItems().addAll(true, false);
        SpinnerValueFactory<Integer> severityValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5);
        severitySpinner.setValueFactory(severityValueFactory);

        // Bind table columns to properties in the Disaster class
        typeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));
        descriptionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
        locationColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLocation()));
        severityColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getSeverity()).asObject());
        criticalColumn.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isCritical()).asObject());
    }

    /**
     * Handles the action of reporting a new disaster.
     * Collects input from the user and adds a new disaster to the table.
     */
    @FXML
    public void reportDisaster() {
        String type = typeComboBox.getValue();
        String description = descriptionField.getText();
        String location = locationField.getText();
        int severity = severitySpinner.getValue();
        boolean isCritical = criticalComboBox.getValue();

        // Create a new disaster and add it to the table
        Disaster newDisaster = new Disaster(type, description, location, severity, isCritical);
        disasterTableView.getItems().add(newDisaster);

        // Simulate notifying departments and show confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Disaster Reported");
        alert.setHeaderText("Disaster Report");
        alert.setContentText("The disaster has been reported successfully by " + user.getUsername());
        alert.showAndWait();
    }

    /**
     * Handles the action of assessing a disaster selected from the table.
     * If a disaster is selected, shows a confirmation message.
     * 
     * @param event The event triggered by the assess disaster button
     */
    @FXML
    public void assessDisaster(ActionEvent event) {
        Disaster selectedDisaster = disasterTableView.getSelectionModel().getSelectedItem();
        if (selectedDisaster != null) {
            // Perform disaster assessment logic
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Disaster Assessment");
            alert.setHeaderText("Assessment Complete");
            alert.setContentText("The selected disaster has been assessed by " + user.getUsername());
            alert.showAndWait();
        } else {
            // Display error if no disaster is selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Disaster Selected");
            alert.setContentText("Please select a disaster from the table to assess.");
            alert.showAndWait();
        }
    }
}