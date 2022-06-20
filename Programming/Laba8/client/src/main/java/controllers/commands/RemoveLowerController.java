package controllers.commands;

import client.Client;
import client.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.Movie;
import data.Response;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

public class RemoveLowerController implements Initializable {
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
                    WindowManager.updateCollection(new LinkedHashSet<> ((HashSet<Movie>) response.object));
                } else {
                    WindowManager.alert(response.message);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
