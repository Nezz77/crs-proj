package model;

import java.util.List;
import java.util.Objects;

public class Course {
    private String courseId;
    private String title;
    private int credits;
    private Department department;
    private int maxCapacity;
    private List<String> prerequisites;  // Stores prerequisite course IDs

    // Constructor with prerequisites
    public Course(String courseId, String title, int credits, Department department, int maxCapacity, List<String> prerequisites) {
        this.courseId = courseId;
        this.title = title;
        this.credits = credits;
        this.department = department;
        this.maxCapacity = maxCapacity;
        this.prerequisites = prerequisites;
    }

    // Constructor without prerequisites
    public Course(String courseId, String title, int credits, Department department, int maxCapacity) {
        this(courseId, title, credits, department, maxCapacity, null);
    }

    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    public List<String> getPrerequisites() { return prerequisites; }
    public void setPrerequisites(List<String> prerequisites) { this.prerequisites = prerequisites; }

    // toString Method
    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", department=" + department +
                ", maxCapacity=" + maxCapacity +
                ", prerequisites=" + (prerequisites != null ? prerequisites : "None") +
                '}';
    }

    // equals Method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return credits == course.credits &&
                maxCapacity == course.maxCapacity &&
                Objects.equals(courseId, course.courseId) &&
                Objects.equals(title, course.title) &&
                Objects.equals(department, course.department) &&
                Objects.equals(prerequisites, course.prerequisites);
    }

    // hashCode Method
    @Override
    public int hashCode() {
        return Objects.hash(courseId, title, credits, department, maxCapacity, prerequisites);
    }
}
