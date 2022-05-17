package commands;

import data.Request;
import server.CollectionManager;
import server.Server;

/**
 * The type Clear command.
 */
public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Clear command.
     *
     * @param collectionManager the collection manager
     */
    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        this.collectionManager.clear();
        Server.print(request.client, "Коллекция очищена\n");
    }
}
