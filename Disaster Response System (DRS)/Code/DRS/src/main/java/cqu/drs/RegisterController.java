package cqu.drs;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller class for the Registration page in the Disaster Response System (DRS).
 * Manages user registration by capturing username and password inputs.
 * After successful registration, the user is redirected to the login page.
 * If registration fails, an error message is displayed.
 * 
 * Author: Mohammad Minhaz Uddin
 */
public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Main mainApp;

    /**
     * Handles the user registration process.
     * If the registration is successful, the user is redirected to the login page.
     * If the registration fails, an error message is shown.
     */
    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean success = DBConnection.registerUser(username, password, "User");
        if (success) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText("User registered successfully!");
            alert.showAndWait();
            mainApp.showLoginPage();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText("Could not register user");
            alert.showAndWait();
        }
    }

    /**
     * Navigates back to the login page when the "Back to Login" button is pressed.
     */
    @FXML
    private void handleBackToLogin() {
        mainApp.showLoginPage();
    }

    /**
     * Sets the main application reference for this controller.
     * 
     * @param mainApp The main application instance.
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
