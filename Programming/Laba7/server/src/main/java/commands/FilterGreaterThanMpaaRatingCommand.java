package commands;

import data.Request;
import server.CollectionManager;
import data.Movie;
import data.MpaaRating;
import server.Server;

import java.util.List;

/**
 * The type Filter greater than mpaa rating command.
 */
public class FilterGreaterThanMpaaRatingCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Filter greater than mpaa rating command.
     *
     * @param collectionManager the collection manager
     */
    public FilterGreaterThanMpaaRatingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        if (request.object == null) {
            Server.requestObject(request.client, MpaaRating.class);
            return;
        }
        MpaaRating mpaaRating = (MpaaRating) request.object;
        List<Movie> movies = collectionManager.filterMovies(movie -> movie.getMpaaRating().compareRatings(mpaaRating) == 1);
        Server.print(request.client, movies + "\n");
    }
}
