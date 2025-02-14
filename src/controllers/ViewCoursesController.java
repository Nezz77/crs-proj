package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
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
    private void initialize() {
        if (courseIdColumn == null || titleColumn == null || creditsColumn == null || courseTable == null) {
            System.out.println("FXML elements were not injected properly!");
            return;
        }

        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        loadCourses();
    }

    private void loadCourses() {
        List<Course> courses = courseService.getAllCourses();
        courseTable.getItems().setAll(courses);
    }
}
