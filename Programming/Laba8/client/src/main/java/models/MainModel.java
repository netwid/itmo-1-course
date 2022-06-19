package models;

import client.Client;
import client.WindowManager;
import data.Response;

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
}
