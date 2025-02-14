package service;

import dao.CourseDAO;
import model.Course;
import java.util.List;

public class CourseService {

    private CourseDAO courseDAO = new CourseDAO();

    // Get all courses
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    // Add a new course
    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }

    // Update an existing course
    public boolean updateCourse(Course course) {
        return courseDAO.updateCourse(course);
    }

    // Delete a course
    public boolean deleteCourse(String courseId) {
        return courseDAO.deleteCourse(courseId);
    }
}
