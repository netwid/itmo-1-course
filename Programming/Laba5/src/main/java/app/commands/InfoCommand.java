package app.commands;

import app.CollectionManager;

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
    public void execute(String[] args) {
        this.collectionManager.info();
    }
}
