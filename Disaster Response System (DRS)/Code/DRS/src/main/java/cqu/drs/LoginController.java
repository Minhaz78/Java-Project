package cqu.drs;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller class for the login page. Handles the login process and navigates
 * to appropriate user dashboards based on role.
 * 
 * @author Mohammad Minhaz Uddin
 */
public class LoginController {

    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;

    private Main mainApp;

    /**
     * Sets the reference to the main application.
     * 
     * @param mainApp Reference to the main application.
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Handles the login button action. Authenticates the user with the entered
     * credentials and navigates to the appropriate dashboard based on the user's role.
     */
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = DBConnection.login(username, password);

        if (user != null) {
            // Show login success message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText("Welcome, " + user.getUsername() + "!");
            alert.setContentText("You have successfully logged in.");
            alert.showAndWait();
            mainApp.navigateBasedOnRole(user);  // Navigate to user/admin dashboard
        } else {
            // Show error message for invalid login
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("Please check your username and password.");
            alert.showAndWait();
        }
    }

    /**
     * Navigates the user to the registration page.
     */
    @FXML
    private void handleRegisterPage() {
        mainApp.showRegisterPage();
    }
}
