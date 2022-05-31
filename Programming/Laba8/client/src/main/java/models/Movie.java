package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;

public class Movie extends RecursiveTreeObject<Movie> {
    private final int id;
    
    public Movie(int id) {
        this.id = id;
    }
}
