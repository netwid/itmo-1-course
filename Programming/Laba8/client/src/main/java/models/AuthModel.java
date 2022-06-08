package models;


import client.Client;
import client.WindowManager;
import data.Response;


public class AuthModel {
    private static AuthModel instance;
    private static String login;
    private static String password;

    private AuthModel() {

    }

    public static AuthModel getInstance() {
        if (instance == null) {
            instance = new AuthModel();
        }
        return instance;
    }

    public void switchToRegister() {
        WindowManager.setScene("Registration", "registerScene");
    }

    public void switchToLogin() {
        WindowManager.setScene("Login", "loginScene");
    }

    public void login(String login, String password) {
        Client.sendCommand("login", login, password);
        Response response = Client.receive();
        if (response.success == false) {
            System.out.println(response.message);
            return;
        }
        AuthModel.login = login;
        AuthModel.password = password;
        WindowManager.setScene("Manager", "main");
    }

    public void register(String login, String password) {
        Client.sendCommand("register", login, password);
        Response response = Client.receive();
        if (response.success == false) {
            System.out.println(response.message);
            return;
        }
        AuthModel.login = login;
        AuthModel.password = password;
        WindowManager.setScene("Manager", "main");
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
