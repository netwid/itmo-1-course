package controllers.commands;

import client.Client;
import client.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.Coordinates;
import data.Movie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

public class ShowController implements Initializable {
    @FXML
    JFXTextField x;

    @FXML
    JFXTextField y;

    @FXML
    JFXButton show;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show.setOnAction(event -> {
            try {
                double x_ = Coordinates.inputX(x.getText());
                int y_ = Coordinates.inputY(y.getText());
                Client.sendCommandObject("show", new Coordinates(x_, y_));
                WindowManager.updateCollection((LinkedHashSet<Movie>) Client.receive().object);
            } catch (Exception e) {

            }
        });
    }
}