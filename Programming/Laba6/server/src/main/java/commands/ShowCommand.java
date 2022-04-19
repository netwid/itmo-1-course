package commands;

import data.Request;
import server.CollectionManager;
import server.Server;

/**
 * The type Show command.
 */
public class ShowCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Show command.
     *
     * @param collectionManager the collection manager
     */
    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        StringBuilder response = new StringBuilder("");

        for (String movie : this.collectionManager.show()) {
            response.append(movie).append('\n');
        }

        Server.print(request.client, String.valueOf(response));
    }
}
