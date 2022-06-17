package controllers;

import client.Client;
import client.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import data.MovieGenre;
import data.MpaaRating;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    @FXML
    private JFXTextField oscarsCount;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField x;
    @FXML
    private JFXTextField y;
    @FXML
    private JFXDatePicker creationDate;
    @FXML
    private ChoiceBox<MovieGenre> genre;
    @FXML
    private ChoiceBox<MpaaRating> mpaaRating;
    @FXML
    private JFXTextField screenwriterName;
    @FXML
    private JFXTextField height;
    @FXML
    private JFXTextField weight;
    @FXML
    private JFXDatePicker birthdayDate;
    @FXML
    private JFXTimePicker birthdayTime;
    @FXML
    private JFXTextField length;
    @FXML
    private JFXTextField passportID;

    @FXML
    private JFXButton remove;

    @FXML
    private JFXButton update;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genre.getItems().setAll(MovieGenre.values());
        mpaaRating.getItems().setAll(MpaaRating.values());

        name.setText(WindowManager.selectedMovie.name.getValue());
        x.setText(String.valueOf(WindowManager.selectedMovie.x.getValue()));
        y.setText(String.valueOf(WindowManager.selectedMovie.y.getValue()));
        creationDate.setValue(LocalDate.parse(WindowManager.selectedMovie.creationDate.getValue()));
        oscarsCount.setText(String.valueOf(WindowManager.selectedMovie.oscarsCount.getValue()));
        length.setText(String.valueOf(WindowManager.selectedMovie.length.getValue()));
        genre.setValue(MovieGenre.valueOf(WindowManager.selectedMovie.genre.getValue()));
        mpaaRating.setValue(MpaaRating.valueOf(WindowManager.selectedMovie.mpaaRating.getValue()));
        screenwriterName.setText(WindowManager.selectedMovie.screenwriterName.getValue());
        birthdayDate.setValue(LocalDateTime.parse(WindowManager.selectedMovie.birthday.getValue()).toLocalDate());
        birthdayTime.setValue(LocalDateTime.parse(WindowManager.selectedMovie.birthday.getValue()).toLocalTime());
        height.setText(String.valueOf(WindowManager.selectedMovie.height.getValue()));
        weight.setText(String.valueOf(WindowManager.selectedMovie.weight.getValue()));
        passportID.setText(String.valueOf(WindowManager.selectedMovie.passportID.getValue()));

        remove.setOnAction(event -> {
            Client.sendCommand("remove_by_id " + WindowManager.selectedMovie.id);
            WindowManager.closePopup();
        });
    }
}
