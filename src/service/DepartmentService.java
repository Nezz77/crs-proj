package service;

import dao.DepartmentDAO;
import model.Department;

import java.sql.SQLException;
import java.util.List;

public class DepartmentService {

    private DepartmentDAO departmentDAO;

    public DepartmentService() {
        departmentDAO = new DepartmentDAO();
    }

    // Add a new department
    public void addDepartment(Department department) throws SQLException {
        departmentDAO.addDepartment(department);
    }

    // Get all departments
    public List<Department> getAllDepartments() throws SQLException {
        return departmentDAO.getAllDepartments();
    }

    // Get department by ID
    public Department getDepartmentById(String departmentId) throws SQLException {
        return departmentDAO.getDepartmentById(departmentId);
    }

    // Update department details
    public void updateDepartment(Department department) throws SQLException {
        departmentDAO.updateDepartment(department);
    }

    // Delete a department
    public void deleteDepartment(String departmentId) throws SQLException {
        departmentDAO.deleteDepartment(departmentId);
    }
}
