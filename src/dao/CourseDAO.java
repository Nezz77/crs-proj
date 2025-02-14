package dao;

import model.Course;
import model.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    
    // Get all courses
    public List<Course> getAllCourses() {
        String query = "SELECT * FROM course";
        List<Course> courses = new ArrayList<>();

        try (Connection conn = dao.DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs, conn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Get a course by ID
    public Course getCourseById(String courseId) {
        String query = "SELECT * FROM course WHERE course_id = ?";
        
        try (Connection conn = dao.DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, courseId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToCourse(rs, conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Add a new course
    public boolean addCourse(Course course) {
        String query = "INSERT INTO course (course_id, title, credits, department_id, max_capacity) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = dao.DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, course.getCourseId());
            pstmt.setString(2, course.getTitle());
            pstmt.setInt(3, course.getCredits());
            pstmt.setString(4, course.getDepartment().getDepartmentId());
            pstmt.setInt(5, course.getMaxCapacity());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update course details
    public boolean updateCourse(Course course) {
        String query = "UPDATE course SET title = ?, credits = ?, department_id = ?, max_capacity = ? WHERE course_id = ?";
        
        try (Connection conn = dao.DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, course.getTitle());
            pstmt.setInt(2, course.getCredits());
            pstmt.setString(3, course.getDepartment().getDepartmentId());
            pstmt.setInt(4, course.getMaxCapacity());
            pstmt.setString(5, course.getCourseId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a course
    public boolean deleteCourse(String courseId) {
        String query = "DELETE FROM course WHERE course_id = ?";
        
        try (Connection conn = dao.DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, courseId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get prerequisites for a course
    private List<String> getPrerequisites(Connection conn, String courseId) throws SQLException {
        List<String> prerequisites = new ArrayList<>();
        String query = "SELECT prerequisite_id FROM course_prerequisite WHERE course_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, courseId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                prerequisites.add(rs.getString("prerequisite_id"));
            }
        }
        return prerequisites;
    }

    // Fetch department details by ID
    private Department getDepartmentById(Connection conn, String departmentId) throws SQLException {
        if (departmentId == null) {
            return null;
        }

        String query = "SELECT department_id, name FROM department WHERE department_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, departmentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Department(
                    rs.getString("department_id"),
                    rs.getString("name")
                );
            }
        }
        return null;
    }

    // Helper function to map a ResultSet row to a Course object
    private Course mapResultSetToCourse(ResultSet rs, Connection conn) throws SQLException {
        Department department = getDepartmentById(conn, rs.getString("department_id"));

        List<String> prerequisites = getPrerequisites(conn, rs.getString("course_id"));

        return new Course(
                rs.getString("course_id"),
                rs.getString("title"),
                rs.getInt("credits"),
                department,
                rs.getInt("max_capacity"),
                prerequisites
        );
    }
}
