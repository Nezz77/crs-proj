// package testDao;

// import model.*;
// import java.sql.SQLException;
// import dao.CourseDAO;
// import dao.DBConnection;
// import dao.DepartmentDAO;
// import dao.EnrollmentDAO;
// import dao.RoleDAO;
// import dao.StudentDAO;
// import dao.UserDAO;

// public class testDao {
//     public static void main(String[] args) {
//         try {
//             System.out.println("Testing DB Connection...");
//             if (DBConnection.getConnection() != null) {
//                 System.out.println("✅ Database connected successfully!");
//             }

            // Testing RoleDAO
            // RoleDAO roleDAO = new RoleDAO();
            // roleDAO.addRole(new Role(4, "user1"));
            // System.out.println("Roles: " + roleDAO.getAllRoles());

            // // Testing UserDAO
            // UserDAO userDAO = new UserDAO();
            // User user = new User("U004", "john_doee", "password1234", new Role(1, "user"));
            // userDAO.addUser(user);
            // System.out.println("User fetched: " + userDAO.getUserById("U004"));

            // // Testing StudentDAO
            // StudentDAO studentDAO = new StudentDAO();
            // Student student = new Student("S004", user, "John Doee", "2000-05-10", "CS", 2, "123456789");
            // studentDAO.addStudent(student);
            // System.out.println("Student fetched: " + studentDAO.getStudentById("S004"));

            // // Testing DepartmentDAO
            // DepartmentDAO departmentDAO = new DepartmentDAO();
            // Department department = new Department("D004", "Computer Science");
            // departmentDAO.addDepartment(department); // Insert department before adding course
            // System.out.println("Departments: " + departmentDAO.getAllDepartments());

            // Testing CourseDAO
            // CourseDAO courseDAO = new CourseDAO();
            // Department department;
            //             Course course = new Course("C004", "Java Programming", 4, department, 50);
            // courseDAO.addCourse(course);
            // System.out.println("Courses: " + courseDAO.getAllCourses());

        //     // Testing EnrollmentDAO
        //     EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        //     Enrollment enrollment = new Enrollment(3, student, course, "Spring 2025", "A");
        //     enrollmentDAO.addEnrollment(enrollment);
        //     System.out.println("Enrollments: " + enrollmentDAO.getAllEnrollments());

//         } catch (SQLException e) {
//          e.printStackTrace();
    
//     }
    
// }
// }
