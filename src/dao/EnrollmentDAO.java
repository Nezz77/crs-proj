package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Enrollment;
import model.Student;

public class EnrollmentDAO {

    private Connection connection;

    public EnrollmentDAO() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all enrollments
    public List<Enrollment> getAllEnrollments() throws SQLException {
        String query = "SELECT * FROM enrollment";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            List<Enrollment> enrollments = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student(rs.getString("student_id"), null, null, null, null, 0, null);  // Assuming a Student constructor
                Course course = new Course(rs.getString("course_id"), null, 0, null, 0);  // Assuming a Course constructor
                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollment_id"),
                        student,
                        course,
                        rs.getString("semester"),
                        rs.getString("grade")
                );
                enrollments.add(enrollment);
            }
            return enrollments;
        }
    }

    // Get a specific enrollment by its ID
    public Enrollment getEnrollmentById(int enrollmentId) throws SQLException {
        String query = "SELECT * FROM enrollment WHERE enrollment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, enrollmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Student student = new Student(rs.getString("student_id"), null, null, null, null, 0, null);
                Course course = new Course(rs.getString("course_id"), null, 0, null, 0);
                return new Enrollment(
                        rs.getInt("enrollment_id"),
                        student,
                        course,
                        rs.getString("semester"),
                        rs.getString("grade")
                );
            } else {
                return null;
            }
        }
    }

    // Add a new enrollment
    public boolean addEnrollment(Enrollment enrollment) throws SQLException {
        String query = "INSERT INTO enrollment (student_id, course_id, semester, grade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, enrollment.getStudent().getStudentId());
            stmt.setString(2, enrollment.getCourse().getCourseId());
            stmt.setString(3, enrollment.getSemester());
            stmt.setString(4, enrollment.getGrade());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Update an enrollment's grade
    public boolean updateEnrollment(Enrollment enrollment) throws SQLException {
        String query = "UPDATE enrollment SET grade = ? WHERE enrollment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, enrollment.getGrade());
            stmt.setInt(2, enrollment.getEnrollmentId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Delete an enrollment by its ID
    public boolean deleteEnrollment(int enrollmentId) throws SQLException {
        String query = "DELETE FROM enrollment WHERE enrollment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, enrollmentId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Get all enrollments for a specific student
    public List<Enrollment> getEnrollmentsByStudentId(Student student) throws SQLException {
        String query = "SELECT * FROM enrollment WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getStudentId()); // Use student.getStudentId() to set the parameter
            ResultSet rs = stmt.executeQuery();
            List<Enrollment> enrollments = new ArrayList<>();
            while (rs.next()) {
                // Create Student and Course objects from ResultSet
                Student studentFromDb = new Student(rs.getString("student_id"), null, null, null, null, 0, null);
                Course course = new Course(rs.getString("course_id"), null, 0, null, 0);
                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollment_id"),
                        studentFromDb,
                        course,
                        rs.getString("semester"),
                        rs.getString("grade")
                );
                enrollments.add(enrollment);
            }
            return enrollments;
        }
    }

    // Get all enrollments for a specific course (stub method to be implemented)
    public List<Enrollment> getEnrollmentsByCourse(Course course) throws SQLException {
        // Implement this method if necessary
        throw new UnsupportedOperationException("Unimplemented method 'getEnrollmentsByCourse'");
    }

    // Remove an enrollment (stub method to be implemented)
    public boolean removeEnrollment(Enrollment enrollment) throws SQLException {
        // Implement this method if necessary
        throw new UnsupportedOperationException("Unimplemented method 'removeEnrollment'");
    }
}
