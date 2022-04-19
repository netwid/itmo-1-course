package commands;

import data.Request;
import server.CollectionManager;
import data.Movie;
import data.MpaaRating;
import server.Server;

import java.util.List;

/**
 * The type Count less than mpaa rating command.
 */
public class CountLessThanMpaaRatingCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Count less than mpaa rating command.
     *
     * @param collectionManager the collection manager
     */
    public CountLessThanMpaaRatingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        if (request.object == null) {
            Server.requestObject(request.client, MpaaRating.class);
            return;
        }
        MpaaRating mpaaRating = (MpaaRating) request.object;
        List<Movie> movies = collectionManager.filterMovies(movie -> movie.getMpaaRating().compareRatings(mpaaRating) == -1);
        Server.print(request.client, String.valueOf(movies.size()));
    }
}
