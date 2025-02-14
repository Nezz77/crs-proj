package service;

import dao.CourseDAO;
import model.Course;
import java.util.List;

public class CourseService {

    private CourseDAO courseDAO = new CourseDAO();

    // Get all courses
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();  // No SQLException expected
    }

    // Add a new course
    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);  // No SQLException expected
    }
}
