package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    void handleAssignFaculty(ActionEvent event) {

    }

    @FXML
    void handleGenerateReports(ActionEvent event) {

    }

    @FXML
    void handleLogout(ActionEvent event) {
            try {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        Parent root = loader.load();
        
        // Get the current stage
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        
        // Set the new scene and show the stage
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace(); // Handle any exception
    }
        
    }

    @FXML
    void handleManageCourses(ActionEvent event) {
        System.out.println("BUTTON CLICKED");

    }

    @FXML
    void handleManageStudents(ActionEvent event) {

    }

}
