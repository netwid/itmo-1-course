package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.AuthModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private JFXButton loginBtn;
    @FXML
    private Hyperlink registerLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("Listener triggered");
        });

        registerLink.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("Listener2 triggered");
            Stage currentWindow = (Stage) registerLink.getScene().getWindow();
            currentWindow.close();
            AuthModel.getInstance().switchToRegister();
        });
    }
}
