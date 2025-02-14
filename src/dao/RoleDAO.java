package dao;

import model.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    private Connection connection;

    public RoleDAO() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    // Add a new role
    public void addRole(Role role) throws SQLException {
        // Check if the role already exists
        String checkQuery = "SELECT COUNT(*) FROM role WHERE role_name = ?";
        
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, role.getRoleName());
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Role '" + role.getRoleName() + "' already exists.");
                return; // Skip inserting if the role already exists
            }

            // Role does not exist, proceed with insertion
            String insertQuery = "INSERT INTO role (role_name) VALUES (?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, role.getRoleName());
                insertStmt.executeUpdate();

                // Retrieve generated role_id
                try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        role.setRoleId(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    // Get a role by ID
    public Role getRoleById(int roleId) throws SQLException {
        String sql = "SELECT * FROM role WHERE role_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Role(rs.getInt("role_id"), rs.getString("role_name"));
                }
            }
        }
        return null; // Role not found
    }

    // Get all roles
    public List<Role> getAllRoles() throws SQLException {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                roles.add(new Role(rs.getInt("role_id"), rs.getString("role_name")));
            }
        }
        return roles;
    }

    // Update a role
    public void updateRole(Role role) throws SQLException {
        String sql = "UPDATE role SET role_name = ? WHERE role_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, role.getRoleName());
            stmt.setInt(2, role.getRoleId());
            stmt.executeUpdate();
        }
    }

    // Delete a role by ID
    public void deleteRole(int roleId) throws SQLException {
        String sql = "DELETE FROM role WHERE role_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roleId);
            stmt.executeUpdate();
        }
    }
}
