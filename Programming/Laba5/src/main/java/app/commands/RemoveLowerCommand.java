package app.commands;

import app.CollectionManager;

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
    public void execute(String[] args) {
        try {
            int length = Integer.parseInt(args[0]);
            collectionManager.removeLower(length);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Не передана длина");
        }
        catch (NumberFormatException e) {
            System.out.println("Длина должна быть числом");
        }
    }
}
