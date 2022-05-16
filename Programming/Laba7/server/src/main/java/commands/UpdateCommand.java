package commands;

import data.Request;
import server.CollectionManager;
import data.Movie;
import server.Server;

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
    public void execute(Request request) {
        try {
            int id = Integer.parseInt(request.args[0]);
            if (collectionManager.filterMovies(movie -> movie.getId() == id).isEmpty()) {
                Server.print(request.client, "Данного id нет в коллекции\n");
                return;
            }
            Movie movie = Movie.input();
            if (this.collectionManager.update(id, movie))
                Server.print(request.client, "Фильм обновлён\n");
            else
                Server.print(request.client, "Фильм с данным id не найден\n");
        }
        catch (IndexOutOfBoundsException e) {
            Server.print(request.client, "Не указан атрибут id\n");
        }
        catch (NumberFormatException e) {
            Server.print(request.client, "Id должен быть числом\n");
        }
    }
}
