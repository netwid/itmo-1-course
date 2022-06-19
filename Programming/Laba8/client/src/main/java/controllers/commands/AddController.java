package controllers.commands;

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
import java.util.ResourceBundle;

public class AddController implements Initializable {
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
    private JFXButton add;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genre.getItems().setAll(MovieGenre.values());
        mpaaRating.getItems().setAll(MpaaRating.values());

        add.setOnAction(event -> {
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
                LocalDateTime birthday;
                if (birthdayDate.getValue() == null || birthdayTime.getValue() == null)
                    birthday = null;
                else
                    birthday = LocalDateTime.of(birthdayDate.getValue(), birthdayTime.getValue());
                int height_ = Person.inputHeight(height.getText());
                Double weight_ = Person.inputWeight(weight.getText());
                String passportID_ = Person.inputPassportId(passportID.getText());

                Movie movie = new Movie(0, name_, new Coordinates(x_, y_), creationDate_, oscarsCount_, length_, genre_,
                        mpaa, new Person(screenwriterName_, birthday, height_, weight_, passportID_));

                Client.sendCommandObject(WindowManager.addType, movie);
                Response response = Client.receive();
                if (response.success) {
                    WindowManager.updateCollection(WindowManager.getCollection(0, 0));
                } else {
                    WindowManager.alert("Ошибка добавления");
                }
            } catch (Exception e) {

            }
        });
    }
}

