package controllers.commands;

import client.Client;
import client.WindowManager;
import com.jfoenix.controls.JFXButton;
import data.Movie;
import data.MpaaRating;
import data.Response;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

public class FilterGreaterController implements Initializable {
    @FXML
    private ChoiceBox<MpaaRating> mpaaRating;

    @FXML
    private JFXButton filter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mpaaRating.getItems().setAll(MpaaRating.values());
        filter.setOnAction(event -> {
            if (mpaaRating.getValue() != null) {
                Client.sendCommandObject("filter_greater_than_mpaa_rating", mpaaRating.getValue());
                Response response = Client.receive();
                if (response.success) {
                    WindowManager.updateCollection(new LinkedHashSet<>((ArrayList<Movie>) response.object));
                } else {
                    WindowManager.alert(response.message);
                }
            }
        });
    }
}
