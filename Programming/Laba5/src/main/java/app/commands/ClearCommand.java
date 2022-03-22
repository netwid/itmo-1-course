package app.commands;

import app.CollectionManager;

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
    public void execute(String[] args) {
        this.collectionManager.clear();
    }
}
