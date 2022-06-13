package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import data.MovieGenre;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MovieTable extends RecursiveTreeObject<MovieTable> {
    public final IntegerProperty id;
    public final StringProperty name;
    public final DoubleProperty x;
    public final IntegerProperty y;
    public final StringProperty creationDate;
    public final LongProperty oscarsCount;
    public final IntegerProperty length;
    public final StringProperty genre;
    public final StringProperty mpaaRating;
    public final StringProperty screenwriterName;
    public final StringProperty birthday;
    public final IntegerProperty height;
    public final DoubleProperty weight;
    public final StringProperty passportID;


    public MovieTable(int id, String name, double x, int y, LocalDate creationDate, Long oscarsCount, int length,
                      MovieGenre genre, String mpaaRating, String screenwriterName, LocalDateTime birthday, int height,
                      Double weight, String passportID) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.creationDate = new SimpleStringProperty(creationDate.toString());
        this.oscarsCount = new SimpleLongProperty(oscarsCount);
        this.length = new SimpleIntegerProperty(length);
        this.genre = new SimpleStringProperty(genre == null ? "" : genre.name());
        this.mpaaRating = new SimpleStringProperty(mpaaRating);
        this.screenwriterName = new SimpleStringProperty(screenwriterName);
        this.birthday = new SimpleStringProperty(birthday == null ? "" : birthday.toString());
        this.height = new SimpleIntegerProperty(height);
        this.weight = new SimpleDoubleProperty(weight == null ? 0 : weight);
        this.passportID = new SimpleStringProperty(passportID);
    }
}
