import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Login");
        stage.setMaximized(true);

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/loginScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        JFXButton btn = (JFXButton) scene.lookup("#loginBtn");
//        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Stage stage2 = (Stage) btn.getScene().getWindow();
//                stage2.close();
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/registerScene.fxml"));
//                Parent root1 = null;
//                try {
//                    root1 = (Parent) fxmlLoader.load();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//                stage2 = new Stage();
//                stage2.setMaximized(true);
//                stage2.initModality(Modality.APPLICATION_MODAL);
//                stage2.setTitle("Registration");
//                stage2.setScene(new Scene(root1));
//                stage2.show();
//            }
//        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
