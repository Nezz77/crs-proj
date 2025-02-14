package service;

import dao.EnrollmentDAO;
import model.Course;
import model.Enrollment;
import model.Student;

import java.sql.SQLException;
import java.util.List;

public class EnrollmentService {

    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    // Get all enrollments
    public List<Enrollment> getAllEnrollments() throws SQLException {
        return enrollmentDAO.getAllEnrollments();
    }

    // Get enrollments by student
    public List<Enrollment> getEnrollmentsByStudent(Student student) throws SQLException {
        return enrollmentDAO.getEnrollmentsByStudentId(student);
    }

    // Get enrollments by course
    public List<Enrollment> getEnrollmentsByCourse(Course course) throws SQLException {
        return enrollmentDAO.getEnrollmentsByCourse(course);
    }

    // Add a new enrollment
    public boolean addEnrollment(Enrollment enrollment) {
        try {
            return enrollmentDAO.addEnrollment(enrollment);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remove an enrollment (could be for withdrawal, etc.)
    public boolean removeEnrollment(Enrollment enrollment) throws SQLException {
        return enrollmentDAO.removeEnrollment(enrollment);
    }

    public List<Enrollment> getEnrollmentByStudents(Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnrollmentByStudents'");
    }
}
