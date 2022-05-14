package commands;

import data.Request;
import server.CollectionManager;
import server.Server;

public class GetIdCommand implements Command {
    private final CollectionManager collectionManager;

    public GetIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        Server.print(request.client, String.valueOf(this.collectionManager.getId()));
    }
}
