package controllers;

import client.Client;
import client.WindowManager;
import data.Coordinates;
import data.Movie;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import models.MovieTable;

import javax.swing.event.CaretListener;
import java.net.URL;
import java.util.*;

public class VisualizationController implements Initializable {
    @FXML
    public AnchorPane pane;
    private HashMap<Group, Movie> movies = new HashMap<>();
    private Group chosen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Client.sendCommandObject("show", "1234", "1234", new Coordinates(0, 0));
        WindowManager.movies = (LinkedHashSet<Movie>) Client.receive().object;

        int maxLength = Collections.max(WindowManager.movies, Comparator.comparingInt(Movie::getLength)).getLength();
        int minLength = Collections.min(WindowManager.movies, Comparator.comparingInt(Movie::getLength)).getLength();

        double maxX = Collections.max(WindowManager.movies, (first, second) -> (int) (first.getCoordinates().getX() - second.getCoordinates().getX())).getCoordinates().getX();
        double minX = Collections.min(WindowManager.movies, (first, second) -> (int) (first.getCoordinates().getX() - second.getCoordinates().getX())).getCoordinates().getX();

        int maxY = Collections.max(WindowManager.movies, Comparator.comparingInt(movie -> movie.getCoordinates().getY())).getCoordinates().getY();
        int minY = Collections.min(WindowManager.movies, Comparator.comparingInt(movie -> movie.getCoordinates().getY())).getCoordinates().getY();

        for (Movie movie : WindowManager.movies) {
            Group g = cameraFabric(100 + 50 * (movie.getLength() - minLength) / (maxLength - minLength));
            g.setLayoutX(1700 * (movie.getCoordinates().getX() - minX) / (maxX - minX));
            g.setLayoutY(925.0 * (movie.getCoordinates().getY() - minY) / (maxY - minY));
            movies.put(g, movie);
            pane.getChildren().add(g);
        }

        pane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && chosen != null) {
                double saveX = chosen.getLayoutX();
                double saveY = chosen.getLayoutY();
                chosen.setLayoutX(event.getX());
                chosen.setLayoutY(event.getY());

                Optional<ButtonType> result = WindowManager.confirm("Save?");
                if (result.get() == ButtonType.OK){
                    Movie movie = movies.get(chosen);
                    Client.sendCommandObject("update " + movie.getId(), movie.changeCoordinates(new Coordinates(
                            event.getX(), (int) event.getY()
                    )));
                } else {
                    chosen.setLayoutX(saveX);
                    chosen.setLayoutY(saveY);
                }

                chosen = null;
            }
        });

        WindowManager.scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            if (key == KeyCode.ESCAPE) {
                WindowManager.setScene("Main", "main");
            }
        });
    }

    private Group cameraFabric(int length) {
        double size = length;
        Rectangle r = new Rectangle(length, length);
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
            size, size / 2,
            size + size / 2, 0.0,
            size + size / 2, size
        );
        Group g = new Group();
        g.getChildren().add(r);
        g.getChildren().add(polygon);

        g.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Movie value = movies.get(g);
                WindowManager.selectedMovie = new MovieTable(value.getId(), value.getName(),
                        value.getCoordinates().getX(), value.getCoordinates().getY(),
                        value.getCreationDate(), value.getOscarsCount(), value.getLength(),
                        value.getGenre(), value.getMpaaRating().name(), value.getScreenwriter().getName(),
                        value.getScreenwriter().getBirthday(), value.getScreenwriter().getHeight(),
                        value.getScreenwriter().getWeight(), value.getScreenwriter().getPassportID());
                WindowManager.createPopup("Update", "update");
            } else {
                chosen = g;
            }
        });

        g.setOnMouseEntered(event -> {
            RotateTransition  rotateTransition = new RotateTransition(Duration.millis(5000), g);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(720);
            rotateTransition.play();
        });

        return g;
    }
}
