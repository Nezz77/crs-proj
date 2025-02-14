package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.Department;
import service.CourseService;
import java.util.List;

public class CourseController {

    private CourseService courseService = new CourseService();

    @FXML private ListView<Course> courseListView;
    @FXML private TextField courseIdField, titleField, creditsField, departmentField, maxCapacityField;
    @FXML private Button addCourseButton, updateCourseButton, deleteCourseButton;
    

    @FXML
    public void initialize() {
        loadCourses();
        // Initialize button actions
    addCourseButton.setOnAction(event -> handleAddCourse());
    updateCourseButton.setOnAction(event -> handleUpdateCourse());
    deleteCourseButton.setOnAction(event -> handleDeleteCourse());

    // Handle selection in ListView (optional)
    courseListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            populateFields(newSelection);
        }
    });
    }

    private void populateFields(Course course) {
        courseIdField.setText(course.getCourseId());
        titleField.setText(course.getTitle());
        creditsField.setText(String.valueOf(course.getCredits()));
        departmentField.setText(course.getDepartment().getDepartmentId());
        maxCapacityField.setText(String.valueOf(course.getMaxCapacity()));
    }
    

    private void loadCourses() {
        List<Course> courses = courseService.getAllCourses();
        courseListView.getItems().setAll(courses);
    }

    @FXML
    private void handleAddCourse() {
        try {
            Course course = createCourseFromFields();
            if (courseService.addCourse(course)) {
                showInfo("Course Added", "The course has been added successfully.");
                loadCourses();
            } else {
                showError("Error", "Failed to add course.");
            }
        } catch (Exception e) {
            showError("Invalid Input", "Please check your input fields.");
        }
    }

    @FXML
    private void handleUpdateCourse() {
        try {
            Course course = createCourseFromFields();
            if (courseService.updateCourse(course)) {
                showInfo("Course Updated", "Course updated successfully.");
                loadCourses();
            } else {
                showError("Error", "Failed to update course.");
            }
        } catch (Exception e) {
            showError("Invalid Input", "Please check your input fields.");
        }
    }

    @FXML
    private void handleDeleteCourse() {
        String courseId = courseIdField.getText().trim();
        if (!courseId.isEmpty() && courseService.deleteCourse(courseId)) {
            showInfo("Course Deleted", "The course has been deleted.");
            loadCourses();
        } else {
            showError("Error", "Failed to delete course.");
        }
    }

    private Course createCourseFromFields() {
        return new Course(
            courseIdField.getText(),
            titleField.getText(),
            Integer.parseInt(creditsField.getText()),
            new Department(departmentField.getText(), ""),
            Integer.parseInt(maxCapacityField.getText())
        );
    }

    private void showError(String title, String message) {
        showAlert(title, message, Alert.AlertType.ERROR);
    }

    private void showInfo(String title, String message) {
        showAlert(title, message, Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
