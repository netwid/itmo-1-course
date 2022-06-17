package controllers;

import client.Client;
import client.WindowManager;
import com.jfoenix.controls.JFXButton;
import data.Coordinates;
import data.Movie;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import models.AuthModel;
import models.MovieTable;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML public JFXButton visualization;
    @FXML public MenuItem help;
    @FXML public MenuItem info;
    @FXML public MenuItem show;
    @FXML public MenuItem add;
    @FXML public MenuItem clear;
    @FXML public MenuItem executeScript;
    @FXML public MenuItem addIfMax;
    @FXML public MenuItem addIfMin;
    @FXML public MenuItem removeLower;
    @FXML public MenuItem exit;
    @FXML public Label login;
    @FXML public MenuItem russiaLang;
    @FXML public MenuItem canadaLang;
    @FXML public MenuItem norwayLang;
    @FXML public MenuItem polandLang;
    @FXML public ImageView chosenLang;
    @FXML public TableView<MovieTable> movies;
    @FXML public TableColumn<MovieTable, Integer> id;
    @FXML public TableColumn<MovieTable, String> name;
    @FXML public TableColumn<MovieTable, Double> x;
    @FXML public TableColumn<MovieTable, Integer> y;
    @FXML public TableColumn<MovieTable, String> creationDate;
    @FXML public TableColumn<MovieTable, Long> oscarsCount;
    @FXML public TableColumn<MovieTable, Integer> length;
    @FXML public TableColumn<MovieTable, String> genre;
    @FXML public TableColumn<MovieTable, String> mpaaRating;
    @FXML public TableColumn<MovieTable, String> screenwriterName;
    @FXML public TableColumn<MovieTable, String> birthday;
    @FXML public TableColumn<MovieTable, Integer> height;
    @FXML public TableColumn<MovieTable, Double> weight;
    @FXML public TableColumn<MovieTable, String> passportID;

    public ObservableList<MovieTable> masterData = FXCollections.observableArrayList();
    public ObservableList<MovieTable> filteredData = FXCollections.observableArrayList();

    public MainController() {
        Client.sendCommandObject("show", new Coordinates(0, 0));

        LinkedHashSet<Movie> movies = (LinkedHashSet<Movie>) Client.receive().object;
        if (movies != null) {
            movies.forEach(value -> masterData.add(new MovieTable(value.getId(), value.getName(),
                    value.getCoordinates().getX(), value.getCoordinates().getY(),
                    value.getCreationDate(), value.getOscarsCount(), value.getLength(),
                    value.getGenre(), value.getMpaaRating().name(), value.getScreenwriter().getName(),
                    value.getScreenwriter().getBirthday(), value.getScreenwriter().getHeight(),
                    value.getScreenwriter().getWeight(), value.getScreenwriter().getPassportID())));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        help.setOnAction(event -> WindowManager.createPopup("Help", "commands/help"));
        info.setOnAction(event -> WindowManager.createPopup("Info", "commands/info"));
        show.setOnAction(event -> WindowManager.createPopup("Show", "commands/show"));
        add.setOnAction(event -> {
            WindowManager.addType = "add";
            WindowManager.createPopup("Add", "commands/add");
        });
        clear.setOnAction(event -> Client.sendCommand("clear"));
        executeScript.setOnAction(event -> {

        });
        addIfMax.setOnAction(event -> {
            WindowManager.addType = "add_if_max";
            WindowManager.createPopup("Add", "commands/add");
        });
        addIfMin.setOnAction(event -> {
            WindowManager.addType = "add_if_min";
            WindowManager.createPopup("Add", "commands/add");
        });
        removeLower.setOnAction(event -> WindowManager.createPopup("Remove lowe", "commands/removeLower"));
        exit.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        visualization.setOnAction(event -> WindowManager.setScene("Visualization", "visualization"));

        login.setText(AuthModel.getInstance().getLogin());

        chosenLang.setImage(new Image(getClass().getResourceAsStream("/flags/" + WindowManager.getLocale() + ".png")));

        russiaLang.setOnAction(event -> {
            chosenLang.setImage(new Image(getClass().getResourceAsStream("/flags/ru_RU.png")));
            WindowManager.changeLocale(new Locale("ru", "RU"));
        });

        canadaLang.setOnAction(event -> {
            chosenLang.setImage(new Image(getClass().getResourceAsStream("/flags/en_CA.png")));
            WindowManager.changeLocale(new Locale("en", "CA"));
        });

        norwayLang.setOnAction(event -> {
            chosenLang.setImage(new Image(getClass().getResourceAsStream("/flags/nb_NO.png")));
            WindowManager.changeLocale(new Locale("nb", "NO"));
        });

        polandLang.setOnAction(event -> {
            chosenLang.setImage(new Image(getClass().getResourceAsStream("/flags/pl_PL.png")));
            WindowManager.changeLocale(new Locale("pl", "PL"));
        });

        id.setCellValueFactory(cellData -> cellData.getValue().id.asObject());
        name.setCellValueFactory(cellData -> cellData.getValue().name);
        x.setCellValueFactory(cellData -> cellData.getValue().x.asObject());
        y.setCellValueFactory(cellData -> cellData.getValue().y.asObject());
        creationDate.setCellValueFactory(cellData -> cellData.getValue().creationDate);
        oscarsCount.setCellValueFactory(cellData -> cellData.getValue().oscarsCount.asObject());
        length.setCellValueFactory(cellData -> cellData.getValue().length.asObject());
        genre.setCellValueFactory(cellData -> cellData.getValue().genre);
        mpaaRating.setCellValueFactory(cellData -> cellData.getValue().mpaaRating);
        screenwriterName.setCellValueFactory(cellData -> cellData.getValue().screenwriterName);
        birthday.setCellValueFactory(cellData -> cellData.getValue().birthday);
        height.setCellValueFactory(cellData -> cellData.getValue().height.asObject());
        weight.setCellValueFactory(cellData -> cellData.getValue().weight.asObject());
        passportID.setCellValueFactory(cellData -> cellData.getValue().passportID);

        movies.setItems(masterData);

        movies.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
                    WindowManager.selectedMovie = movies.getSelectionModel().getSelectedItem();
                    WindowManager.createPopup("Update", "update");
                }
            }
        });
    }
}
