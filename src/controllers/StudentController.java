package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentController {

    @FXML
    void handleDropCourse(ActionEvent event) {
        loadScene(event, "/view/drop_course.fxml");
    }

    @FXML
    void handleLogout(ActionEvent event) {
        loadScene(event, "/view/login.fxml");

    }

    @FXML
    void handleRegisterForCourse(ActionEvent event) {
        loadScene(event, "/view/register_course.fxml");
    }

    @FXML
    void handleViewAcademicRecord(ActionEvent event) {
        loadScene(event, "/view/view_academic_record.fxml");
    }

    @FXML
    void handleViewCourses(ActionEvent event) {
        loadScene(event, "/view/view_courses.fxml");
    }
    private void loadScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // Print error for debugging
        }
    }

}



// package controllers;

// import model.Student;
// import service.StudentService;

// import java.sql.SQLException;
// import java.util.List;

// public class StudentController {

//     private StudentService studentService;

//     public StudentController() {
//         studentService = new StudentService();
//     }

//     // Get all students
//     public List<Student> getAllStudents() {
//         try {
//             return studentService.getAllStudents();
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return null;
//         }
//     }

//     // Get a specific student by studentId
//     public Student getStudentById(String studentId) {
//         try {
//             return studentService.getStudentById(studentId);
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return null;
//         }
//     }

//     // Add a new student
//     public boolean addStudent(Student student) {
//         try {
//             return studentService.addStudent(student);
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     // Update an existing student
//     public boolean updateStudent(String studentId, Student student) {
//         student.setStudentId(studentId); // Ensuring student ID is passed correctly
//         try {
//             return studentService.updateStudent(student);
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     // Delete a student by studentId
//     public boolean deleteStudent(String studentId) {
//         try {
//             return studentService.deleteStudent(studentId);
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }
// }
