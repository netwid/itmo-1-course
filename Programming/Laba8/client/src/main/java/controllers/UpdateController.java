package controllers;

import client.Client;
import client.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import data.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
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
        if (!Objects.equals(WindowManager.selectedMovie.genre.getValue(), ""))
            genre.setValue(MovieGenre.valueOf(WindowManager.selectedMovie.genre.getValue()));
        mpaaRating.setValue(MpaaRating.valueOf(WindowManager.selectedMovie.mpaaRating.getValue()));
        screenwriterName.setText(WindowManager.selectedMovie.screenwriterName.getValue());
        if (!Objects.equals(WindowManager.selectedMovie.birthday.getValue(), "")) {
            birthdayDate.setValue(LocalDateTime.parse(WindowManager.selectedMovie.birthday.getValue()).toLocalDate());
            birthdayTime.setValue(LocalDateTime.parse(WindowManager.selectedMovie.birthday.getValue()).toLocalTime());
        }
        height.setText(String.valueOf(WindowManager.selectedMovie.height.getValue()));
        weight.setText(String.valueOf(WindowManager.selectedMovie.weight.getValue()));
        passportID.setText(String.valueOf(WindowManager.selectedMovie.passportID.getValue()));

        remove.setOnAction(event -> {
            Client.sendCommand("remove_by_id " + WindowManager.selectedMovie.id.getValue());
            WindowManager.closePopup();
        });

        update.setOnAction(event -> {
            try {
                String name_ = Movie.inputName(name.getText());
                double x_ = Coordinates.inputX(x.getText());
                int y_ = Coordinates.inputY(y.getText());
                LocalDate creationDate_ = creationDate.getValue();
                long oscarsCount_ = Movie.inputOscars(oscarsCount.getText());
                int length_ = Movie.inputLength(length.getText());
                MovieGenre genre_ = genre.getValue();
                MpaaRating mpaa = MpaaRating.input(mpaaRating.getValue());
                String screenwriterName_ = Person.inputName(screenwriterName.getText());
                LocalDateTime birthday = LocalDateTime.of(birthdayDate.getValue(), birthdayTime.getValue());
                int height_ = Person.inputHeight(height.getText());
                Double weight_ = Person.inputWeight(weight.getText());
                String passportID_ = Person.inputPassportId(passportID.getText());

                Movie movie = new Movie(WindowManager.selectedMovie.id.getValue(), name_, new Coordinates(x_, y_), creationDate_, oscarsCount_, length_, genre_,
                        mpaa, new Person(screenwriterName_, birthday, height_, weight_, passportID_));

                Client.sendCommandObject("update " + WindowManager.selectedMovie.id.getValue(), movie);
                Response response = Client.receive();
                if (response.success) {
                    WindowManager.updateCollection(WindowManager.getCollection(0, 0));
                } else {
                    WindowManager.alert(response.message);
                }
            } catch (Exception e) {

            }
        });
    }
}
