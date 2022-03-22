package app.commands;

import app.CollectionManager;
import app.data.Movie;

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
    public void execute(String[] args) {
        Movie newMovie = Movie.input(collectionManager);
        List<Movie> movies = collectionManager.filterMovies(movie -> movie.getLength() <= newMovie.getLength());
        if (!movies.isEmpty()) {
            System.out.println("К сожалению, в коллекции есть фильм с большим length");
        }
        else {
            collectionManager.add(newMovie);
            System.out.println("Фильм добавлен");
        }
    }
}
