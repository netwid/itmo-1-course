package models;

import client.Client;
import client.WindowManager;
import data.Movie;
import data.Response;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class MainModel {
    public static void executeScript() {

    }
    public static void printFieldDescendingGenre() {
        Client.sendCommand("print_field_descending_genre");
        Response response = Client.receive();
        if (!response.success)
            WindowManager.alert(response.message);
        else
            System.out.println(response.object);
    }

    public static void clear() {
        Client.sendCommand("clear");
        Response response = Client.receive();
        if (!response.success)
            WindowManager.alert(response.message);
        else
            WindowManager.updateCollection(new LinkedHashSet<>((HashSet<Movie>) response.object));
    }
}
