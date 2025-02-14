package model;

import java.util.Objects;

public class Student {
    private String studentId;
    private User user;
    private String name;
    private String dob;
    private String program;
    private int year;
    private String contact;

    public Student(String studentId, User user, String name, String dob, String program, int year, String contact) {
        this.studentId = studentId;
        this.user = user;
        this.name = name;
        this.dob = dob;
        this.program = program;
        this.year = year;
        this.contact = contact;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // toString Method
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", program='" + program + '\'' +
                ", year=" + year +
                ", contact='" + contact + '\'' +
                '}';
    }

    // equals Method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return year == student.year && Objects.equals(studentId, student.studentId) && Objects.equals(user, student.user) && Objects.equals(name, student.name) && Objects.equals(dob, student.dob) && Objects.equals(program, student.program) && Objects.equals(contact, student.contact);
    }

    // hashCode Method
    @Override
    public int hashCode() {
        return Objects.hash(studentId, user, name, dob, program, year, contact);
    }
}
