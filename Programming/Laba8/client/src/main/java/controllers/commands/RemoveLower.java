package controllers.commands;

import client.Client;
import client.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.Movie;
import data.Response;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

public class RemoveLower implements Initializable {
    @FXML
    JFXTextField length;

    @FXML
    JFXButton remove;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        remove.setOnAction(event -> {
            try {
                int length_ = Movie.inputLength(length.getText());
                Client.sendCommand("remove_lower " + length_);
                Response response = Client.receive();
                if (response.success) {
                    WindowManager.updateCollection((LinkedHashSet<Movie>) response.object);
                } else {
                    WindowManager.alert(response.message);
                }
            } catch (Exception e) {

            }
        });
    }
}
