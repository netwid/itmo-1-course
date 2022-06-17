package client;

import data.Movie;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.MovieTable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

class UTF8Control extends ResourceBundle.Control {
    public ResourceBundle newBundle
            (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IOException
    {
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, "properties");
        ResourceBundle bundle = null;
        InputStream stream = null;
        if (reload) {
            URL url = loader.getResource(resourceName);
            if (url != null) {
                URLConnection connection = url.openConnection();
                if (connection != null) {
                    connection.setUseCaches(false);
                    stream = connection.getInputStream();
                }
            }
        } else {
            stream = loader.getResourceAsStream(resourceName);
        }
        if (stream != null) {
            try {
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
            } finally {
                stream.close();
            }
        }
        return bundle;
    }
}

public class WindowManager {
    private static Stage root;
    private static Stage popup = new Stage();
    private static String lastTitle;
    private static String lastFxml;
    private static Alert alert;
    private static Locale locale = new Locale("en", "CA");
    public static LinkedHashSet<Movie> movies;
    public static MovieTable selectedMovie;
    private static final int BORDER_SIZE = 55;

    public static void setRoot(Stage root) {
        WindowManager.root = root;
    }

    public static void setScene(String title, String fxml) {
        lastTitle = title;
        lastFxml = fxml;

        ResourceBundle bundle = ResourceBundle.getBundle("locales/locale", locale, new UTF8Control());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(WindowManager.class.getResource("/" + fxml + ".fxml"));
        loader.setResources(bundle);

        WindowManager.root.setTitle(title);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        try {
            WindowManager.root.setScene(new Scene(loader.load(), screenSize.getWidth(), screenSize.getHeight() - BORDER_SIZE));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createPopup(String title, String fxml) {
        popup.close();
        popup = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("locales/locale", locale, new UTF8Control());

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

    public static void createMessage() {

    }

    public static void changeLocale(Locale locale) {
        WindowManager.locale = locale;
        setScene(lastTitle, lastFxml);
    }

    public static Locale getLocale() {
        return locale;
    }
}
