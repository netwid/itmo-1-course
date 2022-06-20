package commands;

import data.Coordinates;
import data.Movie;
import data.Request;
import server.CollectionManager;
import server.Server;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

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
            Server.error(request.client, "Не переданы координаты");
            return;
        }
        Coordinates coordinates = (Coordinates) request.object;

        LinkedHashSet<Movie> response = this.collectionManager.getAll()
            .stream()
            .sorted(Comparator.comparingDouble(x -> x.coordinatesTo(coordinates)))
            .collect(Collectors.toCollection(LinkedHashSet::new));

        Server.sendObject(request.client, response);
    }
}
