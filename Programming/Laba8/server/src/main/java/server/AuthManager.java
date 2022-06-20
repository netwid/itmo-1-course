package server;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public static String getLogin(String responseBody) {
        try {
            JSONObject album = new JSONObject(responseBody);
            return album.getString("login");
        } catch (Exception e) {
            return null;
        }
    }

    public static String yandex(String token) {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try {
            URL url = new URL("https://login.yandex.ru/info?oauth_token=" + token);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

            String login = getLogin(responseContent.toString());
            AuthManager.register(login, token);
            if (login != null)
                return login;
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}
