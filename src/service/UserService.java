package service;

import dao.UserDAO;
import model.User;
import java.sql.SQLException;

public class UserService {

    private final UserDAO userDAO;

    public UserService() throws SQLException {
        this.userDAO = new UserDAO(); // Initialize DAO (might throw SQLException)
    }

    public User authenticate(String username, String password) throws SQLException {
        return userDAO.authenticateUser(username, password);
    }
}
