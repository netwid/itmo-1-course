package commands;

import data.Request;
import server.CollectionManager;
import server.Server;

/**
 * The type Print field descending genre.
 */
public class PrintFieldDescendingGenre implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Print field descending genre.
     *
     * @param collectionManager the collection manager
     */
    public PrintFieldDescendingGenre(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        Server.print(request.client, this.collectionManager.printFieldDescendingGenre());
    }
}
