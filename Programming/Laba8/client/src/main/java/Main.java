import controllers.WindowManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Login");
        stage.setMaximized(true);

        WindowManager.setRoot(stage);
        WindowManager.setScene("Login", "loginScene");

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
