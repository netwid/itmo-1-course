package models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthModel {
    private static AuthModel instance;

    private AuthModel() {

    }

    public static AuthModel getInstance() {
        if (instance == null) {
            instance = new AuthModel();
        }
        return instance;
    }

    public void switchToRegister() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/registerScene.fxml"));
//        loader.setResources(ResourceBundle.getBundle("client.resources.Locale", Locale.getDefault()));
        Stage mainWindow = new Stage();
        mainWindow.setMaximized(true);
        mainWindow.setTitle("Registration");
        try {
            mainWindow.setScene(new Scene(loader.load()));
        }
        catch (IOException e) {

        }
        mainWindow.show();
    }

    public void switchToLogin() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/loginScene.fxml"));
//        loader.setResources(ResourceBundle.getBundle("client.resources.Locale", Locale.getDefault()));
        Stage mainWindow = new Stage();
        mainWindow.setMaximized(true);
        mainWindow.setTitle("Login");
        try {
            mainWindow.setScene(new Scene(loader.load()));
        }
        catch (IOException e) {

        }
        mainWindow.show();
    }
}
