package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.AuthModel;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private JFXButton registerBtn;
    @FXML
    private Hyperlink loginLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("Listener triggered");
        });

        loginLink.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            AuthModel.getInstance().switchToLogin();
        });
    }
}
