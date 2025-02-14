package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Course;
import service.CourseService;
import java.util.List;

public class ViewCoursesController {

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> courseIdColumn;

    @FXML
    private TableColumn<Course, String> titleColumn;

    @FXML
    private TableColumn<Course, Integer> creditsColumn;

    private final CourseService courseService = new CourseService();
    @FXML
    void handleBackButton(ActionEvent event) {
        SceneLoader.loadScene(event, "/view/student_panel.fxml");
    }

    @FXML
    private void initialize() {
        try {
            if (courseTable == null || courseIdColumn == null || titleColumn == null || creditsColumn == null) {
                throw new IllegalStateException("FXML components were not properly injected!");
            }

            courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
            
            loadCourses();
        } catch (Exception e) {
            System.err.println("Error initializing ViewCoursesController: " + e.getMessage());
            e.printStackTrace();
        }
    }
     

    

    private void loadCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            ObservableList<Course> courseList = FXCollections.observableArrayList(courses);
            courseTable.setItems(courseList);
        } catch (Exception e) {
            System.err.println("Error loading courses: " + e.getMessage());
            e.printStackTrace();
        }
    }
}