package controllers;

import client.Client;
import client.WindowManager;
import data.Coordinates;
import data.Movie;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.swing.event.CaretListener;
import java.net.URL;
import java.util.*;

public class VisualizationController implements Initializable {
    @FXML
    public AnchorPane pane;
    private HashMap<Group, Movie> movies = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Client.sendCommandObject("show", "1234", "1234", new Coordinates(0, 0));
        WindowManager.movies = (LinkedHashSet<Movie>) Client.receive().object;

        int maxLength = Collections.max(WindowManager.movies, Comparator.comparingInt(Movie::getLength)).getLength();
        int minLength = Collections.min(WindowManager.movies, Comparator.comparingInt(Movie::getLength)).getLength();

        double maxX = Collections.max(WindowManager.movies, (first, second) -> (int) (first.getCoordinates().getX() - second.getCoordinates().getX())).getCoordinates().getX();
        double minX = Collections.min(WindowManager.movies, (first, second) -> (int) (first.getCoordinates().getX() - second.getCoordinates().getX())).getCoordinates().getX();

        int maxY = Collections.max(WindowManager.movies, (first, second) -> (int) (first.getCoordinates().getY() - second.getCoordinates().getY())).getCoordinates().getY();
        int minY = Collections.min(WindowManager.movies, (first, second) -> (int) (first.getCoordinates().getY() - second.getCoordinates().getY())).getCoordinates().getY();


        for (Movie movie : WindowManager.movies) {
            Group g = cameraFabric(100 + 50 * (movie.getLength() - minLength) / (maxLength - minLength));
            g.setLayoutX(1900 * (movie.getCoordinates().getX() - minX) / (maxX - minX));
            g.setLayoutY(1000.0 * (movie.getCoordinates().getY() - minY) / (maxY - minY));
            movies.put(g, movie);
            pane.getChildren().add(g);
        }
//        pane.setOnMouseClicked();
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
            System.out.println("Clicked");
        });

        g.setOnMouseEntered(event -> {
            System.out.println("Entered");
            RotateTransition  rotateTransition = new RotateTransition(Duration.millis(5000), g);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(720);
            rotateTransition.play();
        });

        return g;
    }
}
