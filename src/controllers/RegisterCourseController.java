package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import service.CourseService;
import service.EnrollmentService;

public class RegisterCourseController {

    @FXML private ComboBox<String> courseComboBox;
    @FXML private ComboBox<String> semesterComboBox;
    @FXML private Button registerButton;
    @FXML private Button backButton;

    private CourseService courseService;
    private EnrollmentService enrollmentService;

    public RegisterCourseController() {
        this.courseService = new CourseService();
        this.enrollmentService = new EnrollmentService();
    }

    @FXML
    public void initialize() {
        // Populate course ComboBox with available courses from the database
        courseComboBox.getItems().setAll(courseService.getAllCourses());
    }

    @FXML
    void handleRegisterCourse(ActionEvent event) {
        String selectedCourse = courseComboBox.getValue();
        String semester = semesterComboBox.getValue();
        
        if (selectedCourse != null && semester != null) {
            // Call the service to register the course
            boolean isRegistered = enrollmentService.registerCourse("student_id_here", selectedCourse, semester); // replace with actual student ID

            if (isRegistered) {
                // Registration success, show a success message or update UI
                System.out.println("Course Registered Successfully");
            } else {
                // Registration failed, show an error message
                System.out.println("Failed to Register Course");
            }
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        SceneLoader.loadScene(event, "/view/student_panel.fxml"); // Go back to student panel
    }
}
