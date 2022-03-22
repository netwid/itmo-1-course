package app.commands;

import app.CollectionManager;
import app.data.Movie;

/**
 * The type Update command.
 */
public class UpdateCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Update command.
     *
     * @param collectionManager the collection manager
     */
    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        try {
            int id = Integer.parseInt(args[0]);
            if (collectionManager.filterMovies(movie -> movie.getId() == id).isEmpty()) {
                System.out.println("Данного id нет в коллекции");
                return;
            }
            Movie movie = Movie.input(collectionManager);
            if (this.collectionManager.update(id, movie))
                System.out.println("Фильм обновлён");
            else
                System.out.println("Фильм с данным id не найден");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Не указан атрибут id");
        }
        catch (NumberFormatException e) {
            System.out.println("Id должен быть числом");
        }
    }
}
