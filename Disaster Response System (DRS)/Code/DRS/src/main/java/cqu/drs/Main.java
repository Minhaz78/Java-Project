package cqu.drs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for the Disaster Response System (DRS).
 * Handles the initialization of the JavaFX application and manages
 * navigation between different pages (login, register, user dashboard, admin dashboard).
 * 
 * @author Mohammad Minhaz Uddin
 */
public class Main extends Application {

    private Stage primaryStage;

    /**
     * Entry point for the JavaFX application. 
     * Displays the login page when the application starts.
     * 
     * @param primaryStage The main stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginPage();  // Initially show the login page
    }

    /**
     * Displays the login page.
     */
    public void showLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("login.fxml"));
            AnchorPane loginPage = loader.load();

            Scene scene = new Scene(loginPage);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Link the controller
            LoginController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the registration page.
     */
    public void showRegisterPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("register.fxml"));
            AnchorPane registerPage = loader.load();

            Scene scene = new Scene(registerPage);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Link the controller
            RegisterController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the user dashboard for reporting disasters.
     */
    public void showUserDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("disaster_report.fxml"));
            AnchorPane userDashboard = loader.load();

            Scene scene = new Scene(userDashboard);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Link the controller
            UserController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the admin dashboard for assessing disasters.
     */
    public void showAdminDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("admin.fxml"));
            AnchorPane adminDashboard = loader.load();

            Scene scene = new Scene(adminDashboard);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Link the controller
            AdminController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigates the user to the appropriate dashboard based on their role (Admin or User).
     * 
     * @param user The logged-in user.
     */
    public void navigateBasedOnRole(User user) {
        if ("Admin".equals(user.getRole())) {
            showAdminDashboard();
        } else {
            showUserDashboard();
        }
    }

    /**
     * Main entry point for launching the JavaFX application.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
