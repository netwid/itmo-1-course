package commands;

import data.Request;
import server.CollectionManager;
import server.Server;

/**
 * The type Remove lower command.
 */
public class RemoveLowerCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Remove lower command.
     *
     * @param collectionManager the collection manager
     */
    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        try {
            int length = Integer.parseInt(request.args[0]);
            collectionManager.removeLower(length, request.login);
        }
        catch (IndexOutOfBoundsException e) {
            Server.print(request.client, "Не передана длина\n");
        }
        catch (NumberFormatException e) {
            Server.print(request.client, "Длина должна быть числом\n");
        }
    }
}
