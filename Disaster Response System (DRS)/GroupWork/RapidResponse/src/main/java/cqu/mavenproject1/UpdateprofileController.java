package cqu.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Controller class for updating the user profile in the profile update view.
 * This class handles the loading of the user's current profile data, allows
 * modifications, and updates the profile in the database.
 *
 * @author asif
 */
public class UpdateprofileController implements Initializable {

    @FXML
    private TextField email;  // Email field for entering the user's email

    @FXML
    private TextField password;  // Password field for entering the user's password

    @FXML
    private ChoiceBox<String> genderchoicebox;  // Gender ChoiceBox for selecting the user's gender

    @FXML
    private DatePicker birthday;  // Birthday DatePicker for selecting the user's birthday

    @FXML
    private Button updateButton;  // Button for submitting the profile update

    @FXML
    private Button gotodashboardButton3;  // Button for navigating back to the dashboard

    @FXML
    private Button logoutButton;  // Button for logging out of the application

    private User loggedInUser;  // Stores the username of the currently logged-in user

    /**
     * Initializes the update profile view, setting up choice boxes, buttons,
     * and loading the user's profile data.
     *
     * @param url the location used to resolve relative paths for the root
     * object
     * @param rb the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate gender choice box with options
        genderchoicebox.getItems().addAll("Male", "Female", "Other");

        // Load the user's profile details
        loadUserProfile();

        // Handle profile update button click
        updateButton.setOnAction(event -> handleUpdateProfile());

        // Handle navigation to the dashboard
        gotodashboardButton3.setOnAction(event -> {
            try {
                App.setRoot("dashboard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Handle logging out and navigating to the login view
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
     * Loads the logged-in user's profile details into the form fields. It
     * fetches the current profile data from the database and populates the
     * input fields.
     */
    private void loadUserProfile() {
        loggedInUser = App.getLoggedInUser();  // Get the username of the currently logged-in user

        String selectSQL = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, loggedInUser.getUsername());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                email.setText(rs.getString("email"));
                password.setText(rs.getString("password"));
                genderchoicebox.setValue(rs.getString("gender"));
                birthday.setValue(rs.getDate("birthday").toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the profile update process. It validates the input fields,
     * updates the user's profile in the database, and provides feedback via an
     * alert message.
     */
    private void handleUpdateProfile() {
        String emailValue = email.getText();
        String passwordValue = password.getText();
        String genderValue = genderchoicebox.getValue();
        String birthdayValue = (birthday.getValue() != null) ? birthday.getValue().toString() : null;

        // Validate form inputs
        if (emailValue.isEmpty() || passwordValue.isEmpty() || genderValue == null || birthdayValue == null) {
            showAlert(Alert.AlertType.ERROR, "Update Error", "Please fill in all fields.");
            return;
        }

        String updateSQL = "UPDATE users SET email = ?, password = ?, gender = ?, birthday = ? WHERE username = ?";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, emailValue);
            pstmt.setString(2, passwordValue);
            pstmt.setString(3, genderValue);
            pstmt.setString(4, birthdayValue);
            pstmt.setString(5, loggedInUser.getUsername());

            pstmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Update Successful", "Profile updated successfully!");

            // Navigate back to the dashboard
            App.setRoot("dashboard");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Update Failed", "An error occurred while updating the profile.");
        }
    }

    /**
     * Displays an alert message to the user for feedback.
     *
     * @param alertType the type of the alert (e.g., ERROR, INFORMATION)
     * @param title the title of the alert dialog
     * @param message the content message of the alert
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
