import service.PasswordHasher;

public class TestHasher {
    public static void main(String[] args) {
        String password = "admin123"; // Original password

        // Hash the password
        String hashedPassword = PasswordHasher.hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);

        // Verify the password
        boolean match = PasswordHasher.verifyPassword(password, hashedPassword);
        System.out.println("Password Matches: " + match);
    }
}
