package controllers.commands;

import client.Client;
import client.WindowManager;
import com.jfoenix.controls.JFXButton;
import data.MpaaRating;
import data.Response;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CountLessController implements Initializable {
    @FXML
    private ChoiceBox<MpaaRating> mpaaRating;

    @FXML
    private JFXButton count;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mpaaRating.getItems().setAll(MpaaRating.values());
        count.setOnAction(event -> {
            if (mpaaRating.getValue() != null) {
                Client.sendCommandObject("count_less_than_mpaa_rating", mpaaRating.getValue());
                Response response = Client.receive();
                WindowManager.alert(response.success ? response.object.toString() : response.message);
            }
        });
    }
}
