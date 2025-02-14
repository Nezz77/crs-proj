import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        // Launch JavaFX application
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            
            // Set the scene and show the stage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Course Registration System");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any exception
        }
    }
}








///////////////password user hashing
/// import dao.UserDAO;
// import model.Role;
// import model.User;
// import service.PasswordHasher;
// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Scene;
// import javafx.scene.Parent;
// import javafx.stage.Stage;
// import java.sql.SQLException;

// public class App extends Application {

//     public static void main(String[] args) {
//         launch(args); // Launch JavaFX application
//     }

//     @Override
//     public void start(Stage primaryStage) {
//         try {
//             // Hash and add users' passwords to the database upon application startup
//             addUsersWithHashedPasswords();

//             // Load the FXML file
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
//             Parent root = loader.load();

//             // Set the scene and show the stage
//             Scene scene = new Scene(root);
//             primaryStage.setScene(scene);
//             primaryStage.setTitle("Course Registration System");
//             primaryStage.show();
//         } catch (Exception e) {
//             System.err.println("Error loading FXML file: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }

//     // Method to add users with hashed passwords to the database
//     private void addUsersWithHashedPasswords() {
//         UserDAO userDAO = null;
//         try {
//             userDAO = new UserDAO(); // Initialize UserDAO
            
//             // Hash passwords and add admin, faculty, and student to the database
//             addUserIfNotExist(userDAO, "U001", "admin", "adminPassword", 1);  // Admin
//             addUserIfNotExist(userDAO, "U002", "faculty", "facultyPassword", 2);  // Faculty
//             addUserIfNotExist(userDAO, "U003", "student", "studentPassword", 3);  // Student
            
//             System.out.println("Users added with hashed passwords successfully!");
//         } catch (SQLException e) {
//             e.printStackTrace();
//             System.err.println("Error adding users to the database: " + e.getMessage());
//         }
//     }

//     // Helper method to add a user if they don't already exist
//     private void addUserIfNotExist(UserDAO userDAO, String userId, String username, String plainPassword, int roleId) throws SQLException {
//         User user = new User(userId, username, plainPassword, new Role(roleId, getRoleNameById(roleId)));
        
//         // Only add if user does not exist
//         if (userDAO.getUserById(userId) == null) {
//             userDAO.addUser(user);  // Adds user with hashed password
//             System.out.println("User " + username + " added successfully.");
//         } else {
//             System.out.println("User " + username + " already exists.");
//         }
//     }

//     // Helper method to get role name by role ID
//     private String getRoleNameById(int roleId) {
//         switch (roleId) {
//             case 1: return "Admin";
//             case 2: return "Faculty";
//             case 3: return "Student";
//             default: return "Unknown";
//         }
//     }
// }
