package commands;

import data.Request;
import server.CollectionManager;
import server.Server;

/**
 * The type Remove by id command.
 */
public class RemoveByIdCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Remove by id command.
     *
     * @param collectionManager the collection manager
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        try {
            if (this.collectionManager.removeById(Integer.parseInt(request.args[0]), request.login))
                Server.print(request.client, "Фильм удалён\n");
            else
                Server.print(request.client, "Фильм с данным id не найден или у вас недостаточно прав\n");
        }
        catch (IndexOutOfBoundsException e) {
            Server.print(request.client, "Не указан атрибут id\n");
        }
        catch (NumberFormatException e) {
            Server.print(request.client, "Id должен быть числом\n");
        }
    }
}