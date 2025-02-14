package service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    // Hash password before storing
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12)); // Strong hash
    }

    // Verify password during login
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
