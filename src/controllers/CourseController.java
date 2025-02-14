package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Course;
import model.Department;
import service.CourseService;

import java.util.List;

public class CourseController {

    private CourseService courseService = new CourseService();

    @FXML
    private ListView<Course> courseListView;

    @FXML
    private TextField courseIdField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField creditsField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField maxCapacityField;

    @FXML
    private Button addCourseButton;

    // Initialize method to load courses into the ListView
    public void initialize() {
        loadCourses();
    }

    // Load all courses and display them in the ListView
    private void loadCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            courseListView.getItems().clear(); // Clear current list
            courseListView.getItems().addAll(courses); // Add all courses to the ListView
        } catch (Exception e) {
            showError("Failed to load courses", e.getMessage());
        }
    }

    // Handle adding a new course
    @FXML
    private void handleAddCourse() {
        try {
            String courseId = courseIdField.getText().trim();
            String title = titleField.getText().trim();
            int credits = Integer.parseInt(creditsField.getText().trim());
            String departmentId = departmentField.getText().trim();
            int maxCapacity = Integer.parseInt(maxCapacityField.getText().trim());

            // Validate inputs
            if (courseId.isEmpty() || title.isEmpty() || departmentId.isEmpty()) {
                showError("Invalid Input", "Please fill all required fields.");
                return;
            }

            // Create a new course object
            Department department = new Department(departmentId, "Dummy Department");
            Course newCourse = new Course(courseId, title, credits, department, maxCapacity);

            // Add the course via the service layer
            boolean success = courseService.addCourse(newCourse);
            if (success) {
                loadCourses();  // Reload the courses after adding
                showInfo("Course Added", "The course has been added successfully.");
            } else {
                showError("Error", "Course could not be added.");
            }
        } catch (NumberFormatException e) {
            showError("Invalid Input", "Please enter valid numbers for credits and max capacity.");
        } catch (Exception e) {
            showError("Error", e.getMessage());
        }
    }

    // Display error messages in an alert box
    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Display information messages in an alert box
    private void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
