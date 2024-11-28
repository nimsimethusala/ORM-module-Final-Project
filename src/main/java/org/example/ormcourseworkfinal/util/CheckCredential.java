package org.example.ormcourseworkfinal.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;

public class CheckCredential {
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Verify the password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public static boolean checkPassword(String plainPassword, ArrayList<String> hashPasswords) {
        for (String hashPassword : hashPasswords) {
            boolean isCorrect = verifyPassword(plainPassword, hashPassword);

            if (isCorrect) {
                return false;
            }
        }
        return true;
    }
}
