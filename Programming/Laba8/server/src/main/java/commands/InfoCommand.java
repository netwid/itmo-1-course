package commands;

import data.Request;
import server.CollectionManager;
import server.Server;

/**
 * The type Info command.
 */
public class InfoCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Info command.
     *
     * @param collectionManager the collection manager
     */
    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        Server.print(request.client, this.collectionManager.info());
    }
}
