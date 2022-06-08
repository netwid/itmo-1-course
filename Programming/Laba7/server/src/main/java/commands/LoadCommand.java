package commands;

import data.Request;
import server.CollectionManager;
import server.Invoker;
import server.Server;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * The type Load command.
 */
public class LoadCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Load command.
     *
     * @param collectionManager the collection manager
     */
    public LoadCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        try {
            collectionManager.load();
        }
        catch (Exception e) {
            System.out.println("Ошибка загрузки коллекции");
        }
    }
}
