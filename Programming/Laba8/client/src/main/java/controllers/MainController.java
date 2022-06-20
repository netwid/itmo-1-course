package controllers;

import client.Client;
import client.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.Coordinates;
import data.Movie;
import data.MovieGenre;
import data.MpaaRating;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import models.AuthModel;
import models.MainModel;
import models.MovieTable;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

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
    @FXML public MenuItem printFieldDescendingGenre;
    @FXML public MenuItem countLess;
    @FXML public MenuItem filterGreater;
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

    @FXML private JFXTextField idFilter;
    @FXML private JFXTextField nameFilter;
    @FXML private JFXTextField xFilter;
    @FXML private JFXTextField yFilter;
    @FXML private JFXTextField creationDateFilter;
    @FXML private JFXTextField oscarsCountFilter;
    @FXML private JFXTextField lengthFilter;
    @FXML private ChoiceBox<String> genreFilter;
    @FXML private ChoiceBox<String> mpaaRatingFilter;
    @FXML private JFXTextField screenwriterNameFilter;
    @FXML private JFXTextField birthdayFilter;
    @FXML private JFXTextField heightFilter;
    @FXML private JFXTextField weightFilter;
    @FXML private JFXTextField passportIdFilter;
    @FXML private JFXButton filterButton;

    public ObservableList<MovieTable> masterData = FXCollections.observableArrayList();
    public ObservableList<MovieTable> filteredData = FXCollections.observableArrayList();

    public MainController() {
        if (WindowManager.movies == null) {
            Client.sendCommandObject("show", new Coordinates(0, 0));
            WindowManager.movies = (LinkedHashSet<Movie>) Client.receive().object;
        }
        if (WindowManager.movies != null) {
            WindowManager.movies.forEach(value -> masterData.add(new MovieTable(value.getId(), value.getName(),
                    value.getCoordinates().getX(), value.getCoordinates().getY(),
                    value.getCreationDate(), value.getOscarsCount(), value.getLength(),
                    value.getGenre(), value.getMpaaRating().name(), value.getScreenwriter().getName(),
                    value.getScreenwriter().getBirthday(), value.getScreenwriter().getHeight(),
                    value.getScreenwriter().getWeight(), value.getScreenwriter().getPassportID())));
        }
        filteredData.addAll(masterData);
        masterData.addListener((ListChangeListener<MovieTable>) change -> updateFilteredData());
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
        clear.setOnAction(event -> MainModel.clear());
        executeScript.setOnAction(event -> MainModel.executeScript());
        addIfMax.setOnAction(event -> {
            WindowManager.addType = "add_if_max";
            WindowManager.createPopup("Add", "commands/add");
        });
        addIfMin.setOnAction(event -> {
            WindowManager.addType = "add_if_min";
            WindowManager.createPopup("Add", "commands/add");
        });
        removeLower.setOnAction(event -> WindowManager.createPopup("Remove lower", "commands/removeLower"));
        printFieldDescendingGenre.setOnAction(event -> MainModel.printFieldDescendingGenre());
        countLess.setOnAction(event -> WindowManager.createPopup("Count less", "commands/countLess"));
        filterGreater.setOnAction(event -> WindowManager.createPopup("Filter greater", "commands/filterGreater"));
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

        movies.setItems(filteredData);

        movies.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
                    WindowManager.selectedMovie = movies.getSelectionModel().getSelectedItem();
                    WindowManager.createPopup("Update", "update");
                }
            }
        });

        filterButton.setOnAction(event -> {
            updateFilteredData();
        });

        genreFilter.getItems().addAll(Arrays.stream(MovieGenre.values()).map(Enum::name).collect(Collectors.toList()));
        genreFilter.getItems().add("GENRE");
        genreFilter.setValue("GENRE");
        mpaaRatingFilter.getItems().addAll(Arrays.stream(MpaaRating.values()).map(Enum::name).collect(Collectors.toList()));
        mpaaRatingFilter.getItems().add("RATING");
        mpaaRatingFilter.setValue("RATING");

        setIntegerFilter(idFilter);
        setFloatFilter(xFilter);
        setIntegerFilter(yFilter);
        setDateFilter(creationDateFilter);
        setIntegerFilter(oscarsCountFilter);
        setIntegerFilter(lengthFilter);
        setDateFilter(birthdayFilter);
        setFloatFilter(weightFilter);
        setIntegerFilter(heightFilter);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static void setFloatFilter(TextField filter) {
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && (!isFloat(newValue) || Float.parseFloat(newValue) < 0 ||
                    (newValue.length() > 1 && newValue.indexOf('.') != 1 && newValue.indexOf("00") == 0) ||
                    newValue.contains("d") || newValue.contains("f") || newValue.contains(" "))) {
                if (oldValue.isEmpty())
                    filter.clear();
                else
                    filter.setText(oldValue);
            }
        });
    }

    public static void setIntegerFilter(TextField filter) {
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && (!isInteger(newValue) || Integer.parseInt(newValue) < 0 ||
                    newValue.indexOf("00") == 0) ||
                    newValue.contains("d") || newValue.contains("f") || newValue.contains(" ")) {
                if (oldValue.isEmpty())
                    filter.clear();
                else
                    filter.setText(oldValue);
            }
        });
    }

    public static void setDateFilter(TextField filter) {
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && (newValue.length() >= 10 && !isDate(newValue))) {
                if (oldValue.isEmpty())
                    filter.clear();
                else
                    filter.setText(oldValue);
            }
        });
    }

    public static boolean isDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void updateFilteredData() {
        filteredData.clear();

        masterData.stream()
                .filter(e -> idFilter.getText().isEmpty() || Integer.toString(e.id.getValue()).contains(idFilter.getText()))
                .filter(e -> nameFilter.getText().isEmpty() || e.name.getValue().contains(nameFilter.getText()))
                .filter(e -> xFilter.getText().isEmpty() || Double.toString(e.x.getValue()).contains(xFilter.getText()))
                .filter(e -> yFilter.getText().isEmpty() || Double.toString(e.y.getValue()).contains(yFilter.getText()))
                .filter(e -> creationDateFilter.getText().isEmpty() || e.creationDate.getValue().contains(creationDateFilter.getText()))
                .filter(e -> oscarsCountFilter.getText().isEmpty() || Long.toString(e.oscarsCount.getValue()).contains(heightFilter.getText()))
                .filter(e -> lengthFilter.getText().isEmpty() || Integer.toString(e.length.getValue()).contains(lengthFilter.getText()))
                .filter(e -> genreFilter.getValue().equals("GENRE") || Objects.equals(e.genre.getValue(), genreFilter.getValue()))
                .filter(e -> mpaaRatingFilter.getValue().equals("RATING") || Objects.equals(e.mpaaRating.getValue(), mpaaRatingFilter.getValue()))
                .filter(e -> screenwriterNameFilter.getText().isEmpty() || e.screenwriterName.getValue().contains(screenwriterNameFilter.getText()))
                .filter(e -> birthdayFilter.getText().isEmpty() || e.birthday.getValue().contains(birthdayFilter.getText()))
                .filter(e -> heightFilter.getText().isEmpty() || e.height.toString().equals(heightFilter.getText()))
                .filter(e -> weightFilter.getText().isEmpty() || e.weight.toString().equals(weightFilter.getText()))
                .filter(e -> passportIdFilter.getText().isEmpty() || e.passportID.getValue().equals(passportIdFilter.getText()))
                .forEach(e -> filteredData.add(e));
    }
}
