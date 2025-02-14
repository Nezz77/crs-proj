package service;

import dao.StudentDAO;
import model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    // Get all students
    public List<Student> getAllStudents() throws SQLException {
        return StudentDAO.getAllStudents();
    }

    // Get a student by student_id
    public Student getStudentById(String studentId) throws SQLException {
        return StudentDAO.getStudentById(studentId);
    }

    // Add a new student
    public boolean addStudent(Student student) throws SQLException {
        return StudentDAO.addStudent(student);
    }

    // Update an existing student
    public boolean updateStudent(Student student) throws SQLException {
        return StudentDAO.updateStudent(student);
    }

    // Delete a student by student_id
    public boolean deleteStudent(String studentId) throws SQLException {
        return StudentDAO.deleteStudent(studentId);
    }

    // Additional business logic methods can be added here...

    // Example: Check if a student already exists by their student_id
    public boolean studentExists(String studentId) throws SQLException {
        Student student = StudentDAO.getStudentById(studentId);
        return student != null;
    }

    // Example: Add multiple students (batch insert)
    public boolean addStudents(List<Student> students) throws SQLException {
        boolean allAdded = true;
        for (Student student : students) {
            if (!addStudent(student)) {
                allAdded = false;
            }
        }
        return allAdded;
    }
}
