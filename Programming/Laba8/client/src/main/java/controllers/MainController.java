package controllers;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.AuthModel;
import models.Movie;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private JFXTreeTableView<Movie> movies;
    @FXML private MenuItem help;
    @FXML private MenuItem info;
    @FXML private MenuItem show;
    @FXML private MenuItem add;
    @FXML private MenuItem clear;
    @FXML private MenuItem executeScript;
    @FXML private MenuItem addIfMax;
    @FXML private MenuItem addIfMin;
    @FXML private MenuItem removeLower;
    @FXML private MenuItem exit;
    @FXML private Label login;
    @FXML private MenuItem russiaLang;
    @FXML private MenuItem canadaLang;
    @FXML private MenuItem norwayLang;
    @FXML private MenuItem polandLang;
    @FXML private ImageView chosenLang;
    @FXML private TreeTableColumn<Movie, Integer> id;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        help.setOnAction(event -> WindowManager.createPopup("Help", "commands/help"));
        info.setOnAction(event -> WindowManager.createPopup("Info", "commands/info"));
        exit.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        login.setText(AuthModel.getInstance().getLogin());

        russiaLang.setOnAction(event -> {
            chosenLang.setImage(new Image(getClass().getResourceAsStream("/flags/russia.png")));
            WindowManager.changeLocale(new Locale("ru", "RU"));
        });

        canadaLang.setOnAction(event -> {
            chosenLang.setImage(new Image(getClass().getResourceAsStream("/flags/canada.png")));
            WindowManager.changeLocale(new Locale("en", "CA"));
        });

        norwayLang.setOnAction(event -> {
            chosenLang.setImage(new Image(getClass().getResourceAsStream("/flags/norway.png")));
            WindowManager.changeLocale(new Locale("nb", "NO"));
        });

        polandLang.setOnAction(event -> {
            chosenLang.setImage(new Image(getClass().getResourceAsStream("/flags/poland.png")));
            WindowManager.changeLocale(new Locale("pl", "PL"));
        });

//        id.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
//
//        TreeItem<Movie> root = new TreeItem<>(new Movie(1));
//        this.movies.setRoot(root);
    }
}
