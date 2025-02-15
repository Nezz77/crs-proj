package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.Course;
import model.Department;
import service.CourseService;

import java.util.List;

public class CourseController {

    private final CourseService courseService = new CourseService();

    @FXML private ListView<Course> courseListView;
    @FXML private TextField courseIdField, titleField, creditsField, departmentField, maxCapacityField;
    @FXML private Button addCourseButton, updateCourseButton, deleteCourseButton;

    @FXML
    void handleback(ActionEvent event) {
        SceneLoader.loadScene(event, "/view/admin_panel.fxml");
    }

    @FXML
    public void initialize() {
        loadCourses();

        // Initialize button actions
        addCourseButton.setOnAction(event -> handleAddCourse());
        updateCourseButton.setOnAction(event -> handleUpdateCourse());
        deleteCourseButton.setOnAction(event -> handleDeleteCourse());

        // Handle selection in ListView
        courseListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });

        // Apply custom cell factory for table-like alignment
        courseListView.setCellFactory(lv -> new ListCell<Course>() {
            @Override
            protected void updateItem(Course course, boolean empty) {
                super.updateItem(course, empty);
                if (empty || course == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Create labels for columns
                    Text courseIdLabel = new Text(course.getCourseId());
                    Text titleLabel = new Text(course.getTitle());
                    Text creditsLabel = new Text(String.valueOf(course.getCredits()));
                    Text departmentLabel = new Text(course.getDepartment().getDepartmentId());
                    Text maxCapacityLabel = new Text(String.valueOf(course.getMaxCapacity()));

                    // Set fixed widths for proper column alignment
                    courseIdLabel.setWrappingWidth(80);
                    titleLabel.setWrappingWidth(150);
                    creditsLabel.setWrappingWidth(60);
                    departmentLabel.setWrappingWidth(100);
                    maxCapacityLabel.setWrappingWidth(80);

                    // Arrange labels in a horizontal row
                    HBox row = new HBox(20, courseIdLabel, titleLabel, creditsLabel, departmentLabel, maxCapacityLabel);
                    row.setSpacing(20); // Maintain column spacing

                    setGraphic(row);
                }
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
        } catch (NumberFormatException e) {
            showError("Invalid Input", "Credits and Capacity must be numbers.");
        } catch (IllegalArgumentException e) {
            showError("Invalid Input", e.getMessage());
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
        } catch (NumberFormatException e) {
            showError("Invalid Input", "Credits and Capacity must be numbers.");
        } catch (IllegalArgumentException e) {
            showError("Invalid Input", e.getMessage());
        }
    }

    @FXML
    private void handleDeleteCourse() {
        String courseId = courseIdField.getText().trim();
        if (courseId.isEmpty()) {
            showError("Missing Input", "Please enter a Course ID to delete.");
            return;
        }
        if (courseService.deleteCourse(courseId)) {
            showInfo("Course Deleted", "The course has been deleted.");
            loadCourses();
        } else {
            showError("Error", "Failed to delete course.");
        }
    }

    private Course createCourseFromFields() {
        String courseId = courseIdField.getText().trim();
        String title = titleField.getText().trim();
        String departmentId = departmentField.getText().trim();

        if (courseId.isEmpty() || title.isEmpty() || departmentId.isEmpty() ||
            creditsField.getText().trim().isEmpty() || maxCapacityField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("All fields are required.");
        }

        int credits = Integer.parseInt(creditsField.getText().trim());
        int maxCapacity = Integer.parseInt(maxCapacityField.getText().trim());

        return new Course(courseId, title, credits, new Department(departmentId, ""), maxCapacity);
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
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
