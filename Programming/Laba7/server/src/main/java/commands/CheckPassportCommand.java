package commands;

import data.Request;
import server.CollectionManager;
import server.Server;

public class CheckPassportCommand implements Command {
    private final CollectionManager collectionManager;

    public CheckPassportCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        if (request.args.length < 1) {
            Server.print(request.client, "Отсутствует обязательный параметр паспорт\n");
            return;
        }
        Server.print(request.client, String.valueOf(collectionManager.containsPassport(request.args[0])));
    }
}
