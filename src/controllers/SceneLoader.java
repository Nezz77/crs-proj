package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {

    // Method to load a scene from the given FXML file
    public static void loadScene(ActionEvent event, String fxmlFile) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource(fxmlFile));
            Parent root = loader.load();
            
            // Get the current stage (window)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Set the new scene
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // Print error for debugging
        }
    }
}
