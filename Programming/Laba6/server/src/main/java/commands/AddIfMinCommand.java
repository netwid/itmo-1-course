package commands;

import data.Request;
import server.CollectionManager;
import data.Movie;
import server.Server;

import java.util.List;

/**
 * The type Add if min command.
 */
public class AddIfMinCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Add if min command.
     *
     * @param collectionManager the collection manager
     */
    public AddIfMinCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        if (request.object == null) {
            Server.requestObject(request.client, Movie.class);
            return;
        }
        Movie newMovie = (Movie) request.object;
        List<Movie> movies = collectionManager.filterMovies(movie -> movie.getLength() <= newMovie.getLength());
        if (!movies.isEmpty()) {
            Server.print(request.client, "К сожалению, в коллекции есть фильм с меньшим length\n");
        }
        else {
            collectionManager.add(newMovie);
            Server.print(request.client, "Фильм добавлен");
        }
    }
}
