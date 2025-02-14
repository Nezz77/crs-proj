package model;

public class Enrollment {
    private int enrollmentId;
    private Student student;
    private Course course;
    private String semester;
    private String grade;

    public Enrollment(int enrollmentId, Student student, Course course, String semester, String grade) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = grade;
    }

    // Getters and Setters
    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // toString Method
    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", student=" + student.getName() +
                ", course=" + course.getTitle() +
                ", semester='" + semester + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    // equals Method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return enrollmentId == that.enrollmentId && student.equals(that.student) && course.equals(that.course) && semester.equals(that.semester) && grade.equals(that.grade);
    }

    // hashCode Method
    @Override
    public int hashCode() {
        return 31 * enrollmentId + student.hashCode() + course.hashCode() + semester.hashCode() + grade.hashCode();
    }
}
