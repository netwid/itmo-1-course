package app.commands;

import app.CollectionManager;

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
    public void execute(String[] args) {
        this.collectionManager.show();
    }
}
