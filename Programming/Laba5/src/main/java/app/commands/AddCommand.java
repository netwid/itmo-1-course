package app.commands;

import app.CollectionManager;
import app.data.Movie;

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
    public void execute(String[] args) {
        Movie movie = Movie.input(collectionManager);
        collectionManager.add(movie);
    }
}
