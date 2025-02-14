package controllers;

import model.Course;
import model.Enrollment;
import model.Student;
import service.EnrollmentService;

import java.sql.SQLException;
import java.util.List;

public class EnrollmentController {

    private EnrollmentService enrollmentService = new EnrollmentService();

    // Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        try {
            return enrollmentService.getAllEnrollments();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all enrollments for a specific student
    public List<Enrollment> getEnrollmentsByStudent(Student student) {
        return enrollmentService.getEnrollmentByStudents(student);
    }

    // Get all enrollments for a specific course
    public List<Enrollment> getEnrollmentsByCourse(Course course) {
        try {
            return enrollmentService.getEnrollmentsByCourse(course);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Add a new enrollment
    public boolean addEnrollment(Enrollment enrollment) {
        return enrollmentService.addEnrollment(enrollment);
    }

    // Update an enrollment's grade
    public boolean updateEnrollmentGrade(Enrollment enrollment) throws SQLException {
        return enrollmentService.addEnrollment(enrollment);  // Assuming addEnrollment also updates if already exists (or you could create a separate method in service)
    }

    // Delete an enrollment by its ID
    public boolean deleteEnrollment(int enrollmentId) {
        try {
            Enrollment enrollment = enrollmentService.getAllEnrollments().stream()
                    .filter(enroll -> enroll.getEnrollmentId() == enrollmentId)
                    .findFirst().orElse(null);

            if (enrollment != null) {
                return enrollmentService.removeEnrollment(enrollment);
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
