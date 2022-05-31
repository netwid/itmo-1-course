package controllers;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class WindowManager {
    static Stage root;
    static Stage popup = new Stage();
    static Alert alert;
    static Locale locale = new Locale("en", "CA");
    static final int BORDER_SIZE = 55;

    public static void setRoot(Stage root) {
        WindowManager.root = root;
    }

    public static void setScene(String title, String fxml) {
        ResourceBundle bundle = ResourceBundle.getBundle("locales/locale", locale);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(WindowManager.class.getResource("/" + fxml + ".fxml"));
        loader.setResources(bundle);

        WindowManager.root.setTitle(title);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        try {
            WindowManager.root.setScene(new Scene(loader.load(), screenSize.getWidth(), screenSize.getHeight() - BORDER_SIZE));
        }
        catch (IOException e) {

        }
    }

    static void createPopup(String title, String fxml) {
        popup.close();
        popup = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("locales/locale", locale);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(WindowManager.class.getResource("/" + fxml + ".fxml"));
        loader.setResources(bundle);

        popup.initOwner(root);

        popup.setTitle(title);
        try {
            popup.setScene(new Scene(loader.load()));
        }
        catch (IOException e) {

        }
        popup.show();
    }

    static void createMessage() {

    }

    static void changeLocale(Locale locale) {
        WindowManager.locale = locale;
    }
}
