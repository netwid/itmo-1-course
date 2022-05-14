package commands;

import data.Coordinates;
import data.Movie;
import data.Request;
import server.CollectionManager;
import server.Server;

import java.util.Comparator;

/**
 * The type Show command.
 */
public class ShowCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Show command.
     *
     * @param collectionManager the collection manager
     */
    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        if (request.object == null) {
            Server.requestObject(request.client, Coordinates.class);
            return;
        }
        Coordinates coordinates = (Coordinates) request.object;

        String response = this.collectionManager.getAll()
            .stream()
            .sorted(Comparator.comparingDouble(x -> x.coordinatesTo(coordinates)))
            .map(Movie::toString)
            .reduce("", (prefix, movieString) -> prefix + movieString + "\n");

        Server.print(request.client, response);
    }
}
