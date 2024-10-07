package cqu.mavenproject1;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App class that initializes the application and manages scene
 * transitions.
 *
 * @author asif
 */
public class App extends Application {

    private static Scene scene;
    private static String loggedInUser;

    /**
     * Sets the logged-in user's username.
     *
     * @param username the username of the logged-in user
     */
    public static void setLoggedInUser(String username) {
        loggedInUser = username;
    }

    /**
     * Gets the logged-in user's username.
     *
     * @return the username of the logged-in user, or null if no user is logged
     * in
     */
    public static String getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Logs out the current user by clearing the logged-in user's username.
     */
    public static void logoutUser() {
        loggedInUser = null;
    }

    /**
     * Starts the JavaFX application and displays the login view.
     *
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file for the view cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        // DatabaseConnector.initializeDatabase();

        // Start with the Login view
        scene = new Scene(loadFXML("login"), 600, 400);  // Adjust size if needed
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    /**
     * Changes the current view to a new FXML file.
     *
     * @param fxml the name of the FXML file (without extension) to load
     * @throws IOException if the FXML file cannot be loaded
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads the FXML file and returns the root parent node for the new scene.
     *
     * @param fxml the name of the FXML file (without extension) to load
     * @return the root node of the FXML file
     * @throws IOException if the FXML file cannot be loaded
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main entry point of the JavaFX application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
