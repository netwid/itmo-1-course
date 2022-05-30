package controllers;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private JFXTreeTableView movies;
    @FXML
    private MenuItem help;
    @FXML
    private MenuItem info;
    @FXML
    private MenuItem show;
    @FXML
    private MenuItem add;
    @FXML
    private MenuItem clear;
    @FXML
    private MenuItem executeScript;
    @FXML
    private MenuItem addIfMax;
    @FXML
    private MenuItem addIfMin;
    @FXML
    private MenuItem removeLower;
    @FXML
    private MenuItem exit;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        help.setOnAction(event -> WindowManager.createPopup("Help", "commands/help"));

        exit.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
