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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class for handling login and sign-up functionality in the login
 * view. This class initializes input fields, validates form data, handles login
 * and sign-up processes, and interacts with the database to authenticate and
 * create user accounts.
 *
 * @author asif
 */
public class LoginController implements Initializable {

    @FXML
    private Button loginButtonmain;  // Button for logging in

    @FXML
    private Button signupbutton;  // Button for signing up a new user

    @FXML
    private TextField usernameField;  // TextField for entering the login username

    @FXML
    private PasswordField userpasswordField;  // PasswordField for entering the login password

    @FXML
    private TextField username;  // TextField for entering the sign-up username

    @FXML
    private TextField email;  // TextField for entering the sign-up email

    @FXML
    private PasswordField newPassword;  // PasswordField for entering the sign-up password

    @FXML
    private ChoiceBox<String> genderChoiceBox;  // ChoiceBox for selecting gender during sign-up

    @FXML
    private DatePicker birthday;  // DatePicker for selecting birthday during sign-up

    private User loggedInUser;  // Stores the logged-in user object

    /**
     * Initializes the login and sign-up view, setting up choice boxes and
     * handling button actions.
     *
     * @param url the location used to resolve relative paths for the root
     * object
     * @param rb the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Add choices to the GenderChoiceBox
        genderChoiceBox.getItems().addAll("Male", "Female", "Other");

        // Handle Log In button action
        loginButtonmain.setOnAction(event -> handleLogin());

        // Handle Sign Up button action
        signupbutton.setOnAction(event -> handleSignUp());
    }

    /**
     * Handles the login process. Validates the login form fields and attempts
     * to authenticate the user against the database.
     */
    private void handleLogin() {
        try {
            App.setRoot("login");
            String username = usernameField.getText();
            String password = userpasswordField.getText();
            // Check if username or password is empty
            if (username.isBlank() || password.isBlank()) {
                showAlert("Login Error", "Username and password cannot be empty.");
                return;  // Stop further execution if fields are empty
            }

            if (login(username, password)) {
                showAlert("Login Successful", "Welcome " + username + "!");
                App.setLoggedInUser(loggedInUser.getUsername());
                App.setRoot("dashboard");
            } else {
                showAlert("Login Failed", "Invalid username or password.");
                App.setRoot("login");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to authenticate the user by checking the username and password
     * against the database.
     *
     * @param username the entered username
     * @param password the entered password
     * @return true if the username and password match a database record, false
     * otherwise
     */
    private boolean login(String username, String password) {
        String selectSQL = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                loggedInUser = new User(
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("gender"),
                        rs.getString("birthday"),
                        rs.getString("role")
                );
                DashboardController.setLoggedInUser(loggedInUser);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Helper method to display an alert pop-up message.
     *
     * @param title the title of the alert
     * @param message the message to display in the alert
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

    /**
     * Handles the sign-up process for creating a new user account. Validates
     * the sign-up form fields and saves the new user's information to the
     * database.
     */
    private void handleSignUp() {
        String usernameValue = username.getText();
        String emailValue = email.getText();
        String passwordValue = newPassword.getText();
        String genderValue = genderChoiceBox.getValue();
        String birthdayValue = (birthday.getValue() != null) ? birthday.getValue().toString() : "Not selected";
        String role = "Visitor"; // Default role for all users

        try {
            if (usernameValue.isEmpty() || emailValue.isEmpty() || passwordValue.isEmpty() || genderValue == null) {
                showAlert("Signup Error", "Please fill in all fields.");
            } else {
                boolean isSignupSuccessful = signup(usernameValue, emailValue, passwordValue, genderValue, birthdayValue, role);
                if (isSignupSuccessful) {
                    showAlert("Signup Successful", "Welcome to Rapid Response, " + usernameValue + "!");
                    App.setLoggedInUser(usernameValue);
                    App.setRoot("dashboard");
                } else {
                    showAlert("Signup Failed", "There was an error signing up.");
                    App.setRoot("login");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registers a new user by saving their information to the database.
     *
     * @param username the entered username
     * @param email the entered email address
     * @param password the entered password
     * @param gender the selected gender
     * @param birthday the selected birthday
     * @param role the default role assigned to the user
     * @return true if the sign-up is successful, false otherwise
     */
    private boolean signup(String username, String email, String password, String gender, String birthday, String role) {
        String insertQuery = "INSERT INTO users (username, email, password, gender, birthday, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, gender);
            stmt.setString(5, birthday);
            stmt.setString(6, role);
            stmt.executeUpdate();

            loggedInUser = new User(username, email, password, gender, birthday, role);
            DashboardController.setLoggedInUser(loggedInUser);
            return true; // Signup successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Signup failed
        }
    }
}
