package dao;

import model.Role;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import service.PasswordHasher;  // Utility class for hashing passwords

public class UserDAO {
    private Connection connection;

    public UserDAO() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    // ✅ Authenticate user (Login)
    public User authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT u.user_id, u.username, u.password, r.role_id, r.role_name " +
                     "FROM user u INNER JOIN role r ON u.role_id = r.role_id " +
                     "WHERE u.username = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHashedPassword = rs.getString("password");
                    if (PasswordHasher.verifyPassword(password, storedHashedPassword)) {
                        Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
                        return new User(rs.getString("user_id"), username, storedHashedPassword, role);
                    }
                }
            }
        }
        return null; // User not found or password incorrect
    }

    // ✅ Add a new user (with password hashing)
    public void addUser(User user) throws SQLException {
        // Check if user already exists
        String checkQuery = "SELECT COUNT(*) FROM user WHERE user_id = ?";
        
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, user.getUserId());
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("User with ID '" + user.getUserId() + "' already exists.");
                return;
            }

            // If user does not exist, insert new user with hashed password
            String sql = "INSERT INTO user (user_id, username, password, role_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, user.getUserId());
                stmt.setString(2, user.getUsername());
                stmt.setString(3, PasswordHasher.hashPassword(user.getPassword())); // Hash password
                stmt.setInt(4, user.getRole().getRoleId());
                stmt.executeUpdate();
            }
        }
    }

    // ✅ Get a user by ID
    public User getUserById(String userId) throws SQLException {
        String sql = "SELECT u.user_id, u.username, u.password, r.role_id, r.role_name " +
                     "FROM user u INNER JOIN role r ON u.role_id = r.role_id WHERE u.user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
                    return new User(rs.getString("user_id"), rs.getString("username"), rs.getString("password"), role);
                }
            }
        }
        return null; // User not found
    }

    // ✅ Get all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.user_id, u.username, u.password, r.role_id, r.role_name " +
                     "FROM user u INNER JOIN role r ON u.role_id = r.role_id";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
                users.add(new User(rs.getString("user_id"), rs.getString("username"), rs.getString("password"), role));
            }
        }
        return users;
    }

    // ✅ Update a user (hash password before storing)
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET username = ?, password = ?, role_id = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, PasswordHasher.hashPassword(user.getPassword())); // Hash password
            stmt.setInt(3, user.getRole().getRoleId());
            stmt.setString(4, user.getUserId());
            stmt.executeUpdate();
        }
    }

    // ✅ Delete a user by ID
    public void deleteUser(String userId) throws SQLException {
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        }
    }
}
