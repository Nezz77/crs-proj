package dao;

import model.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    // Add a department to the database
    public void addDepartment(Department department) throws SQLException {
        String query = "INSERT INTO department (department_id, name) VALUES (?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, department.getDepartmentId());
            preparedStatement.setString(2, department.getName());
            preparedStatement.executeUpdate();
        }
    }

    // Get all departments from the database
    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM department";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Department department = new Department(
                        resultSet.getString("department_id"),
                        resultSet.getString("name")
                );
                departments.add(department);
            }
        }
        return departments;
    }

    // Get a department by ID
    public Department getDepartmentById(String departmentId) throws SQLException {
        String query = "SELECT * FROM department WHERE department_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, departmentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Department(
                            resultSet.getString("department_id"),
                            resultSet.getString("name")
                    );
                }
            }
        }
        return null;  // Return null if no department is found
    }

    // Update a department's details
    public void updateDepartment(Department department) throws SQLException {
        String query = "UPDATE department SET name = ? WHERE department_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getDepartmentId());
            preparedStatement.executeUpdate();
        }
    }

    // Delete a department
    public void deleteDepartment(String departmentId) throws SQLException {
        String query = "DELETE FROM department WHERE department_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, departmentId);
            preparedStatement.executeUpdate();
        }
    }
}
