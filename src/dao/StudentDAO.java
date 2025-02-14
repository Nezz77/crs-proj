package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Student;

public class StudentDAO {

    // Get all students
    public static List<Student> getAllStudents() throws SQLException {
        String query = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String studentId = rs.getString("student_id");
                String name = rs.getString("name");
                String dob = rs.getString("dob"); // Example for DOB
                String program = rs.getString("program"); // Example for Program
                int year = rs.getInt("year"); // Example for Year
                String contact = rs.getString("contact"); // Example for Contact

                students.add(new Student(studentId, null, name, dob, program, year, contact));
            }
        }
        return students;
    }

    // Get student by student_id
    public static Student getStudentById(String studentId) throws SQLException {
        String query = "SELECT * FROM student WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String dob = rs.getString("dob");
                    String program = rs.getString("program");
                    int year = rs.getInt("year");
                    String contact = rs.getString("contact");

                    return new Student(studentId, null, name, dob, program, year, contact);
                } else {
                    return null;
                }
            }
        }
    }

    // Add a new student with duplicate check
    public static boolean addStudent(Student student) throws SQLException {
        // Check if the student already exists
        if (getStudentById(student.getStudentId()) != null) {
            System.out.println("Student with ID " + student.getStudentId() + " already exists.");
            return false; // Student already exists
        }

        String query = "INSERT INTO student (student_id, name, dob, program, year, contact) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getDob());
            stmt.setString(4, student.getProgram());
            stmt.setInt(5, student.getYear());
            stmt.setString(6, student.getContact());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Update an existing student
    public static boolean updateStudent(Student student) throws SQLException {
        // Check if the student exists
        if (getStudentById(student.getStudentId()) == null) {
            System.out.println("Student with ID " + student.getStudentId() + " does not exist.");
            return false; // Student does not exist
        }

        String query = "UPDATE student SET name = ?, dob = ?, program = ?, year = ?, contact = ? WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDob());
            stmt.setString(3, student.getProgram());
            stmt.setInt(4, student.getYear());
            stmt.setString(5, student.getContact());
            stmt.setString(6, student.getStudentId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Delete a student
    public static boolean deleteStudent(String studentId) throws SQLException {
        // Check if the student exists before deleting
        if (getStudentById(studentId) == null) {
            System.out.println("Student with ID " + studentId + " does not exist.");
            return false; // Student does not exist
        }

        String query = "DELETE FROM student WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, studentId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
