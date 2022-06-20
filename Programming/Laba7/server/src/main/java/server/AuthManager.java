package server;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthManager {
    static DatabaseManager dm = DatabaseManager.getInstance();

    public static boolean checkLogin(String login, String password) {
        return dm.existsUser(login, hashPassword(password));
    }

    public static boolean register(String login, String password) {
        return dm.register(login, hashPassword(password));
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger nums = new BigInteger(1, digest);
            return nums.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
