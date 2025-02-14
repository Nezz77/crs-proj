package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.User;
import service.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserService userService;

    public LoginController() {
        // No exception-prone initialization here
    }

    @FXML
    private void initialize() {
        try {
            userService = new UserService();
        } catch (SQLException e) {
            showError("Database Error", "Failed to connect to the database.");
            userService = null; // Prevent null pointer issues
        }
    }

    @FXML
    private void handleLogin() {
        if (userService == null) {
            showError("System Error", "User authentication service is unavailable.");
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Login Failed", "Please enter both username and password.");
            return;
        }

        try {
            User user = userService.authenticate(username, password);
            if (user != null) {
                redirectUser(user.getRole().getRoleName());
            } else {
                showError("Login Failed", "Invalid username or password.");
            }
        } catch (SQLException e) {
            showError("Database Error", "An error occurred while verifying credentials.");
        }
    }

    private void redirectUser(String role) {
        String fxmlFile = switch (role.toLowerCase()) {
            case "admin" -> "/view/admin_panel.fxml";
            case "faculty" -> "/view/faculty_panel.fxml";
            case "student" -> "/view/student_panel.fxml";
            default -> null;
        };

        if (fxmlFile == null) {
            showError("Access Denied", "Unknown role: " + role);
            return;
        }

        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

            // Set the correct controller based on the role
            if (role.equals("admin")) {
                loader.setController(new AdminController());
            } else if (role.equals("faculty")) {
                loader.setController(new FacultyController());
            } else if (role.equals("student")) {
                loader.setController(new StudentController());
            }

            Parent root = loader.load();  // Load the FXML file
            stage.setScene(new Scene(root));  // Set the scene with the loaded root
            stage.show();  // Show the scene
        } catch (IOException e) {
            showError("Navigation Error", "Unable to load dashboard for role: " + role);
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
