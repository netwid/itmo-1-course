package commands;

import data.Movie;
import data.Request;
import server.CollectionManager;
import server.Server;

/**
 * The type Add command.
 */
public class AddCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Add command.
     *
     * @param collectionManager the collection manager
     */
    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        if (request.object == null) {
            Server.requestObject(request.client, Movie.class);
            return;
        }
        Movie movie = (Movie) request.object;
        Server.print(request.client, collectionManager.add(movie) ? "Элемент добавлен\n" : "Ошибка добавления\n");
    }
}
