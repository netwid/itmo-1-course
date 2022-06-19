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
                Server.error(request.client, "Данного id нет в коллекции\n");
                return;
            }
            Movie movie = (Movie) request.object;
            if (this.collectionManager.update(id, movie, request.login))
                Server.sendObject(request.client, collectionManager.getAll());
            else
                Server.error(request.client, "Фильм с данным id не найден или у вас недостаточно прав\n");
        }
        catch (IndexOutOfBoundsException e) {
            Server.error(request.client, "Не указан атрибут id\n");
        }
        catch (NumberFormatException e) {
            Server.error(request.client, "Id должен быть числом\n");
        }
    }
}
