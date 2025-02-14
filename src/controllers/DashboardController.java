package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardController {

    @FXML
    private Button manageCoursesButton;

    @FXML
    private Button manageStudentsButton;

    @FXML
    private Button enrollStudentButton;

    @FXML
    private Button logoutButton;

    // Method to navigate to Course Management screen
    @FXML
    private void goToCourses() {
        // Logic to switch to course management view (CourseManagement.fxml)
        System.out.println("Navigating to Course Management...");
        // You can use FXMLLoader to load the new FXML view, or change scenes
    }

    // Method to navigate to Student Management screen
    @FXML
    private void goToStudents() {
        // Logic to switch to student management view (StudentManagement.fxml)
        System.out.println("Navigating to Student Management...");
        // Similar logic for switching views
    }

    // Method to navigate to Enrollment screen
    @FXML
    private void goToEnrollments() {
        // Logic to switch to enrollment management view (EnrollmentManagement.fxml)
        System.out.println("Navigating to Enrollment Management...");
        // Similar logic for switching views
    }

    // Method for handling logout
    @FXML
    private void handleLogout() {
        // Logic for logging out the user and redirecting to login screen
        System.out.println("Logging out...");
        // You can clear session data and navigate back to the login screen (Login.fxml)
    }
}
