package app.commands;

import app.CollectionManager;

/**
 * The type Remove by id command.
 */
public class RemoveByIdCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Remove by id command.
     *
     * @param collectionManager the collection manager
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (this.collectionManager.removeById(Integer.parseInt(args[0])))
                System.out.println("Фильм удалён");
            else
                System.out.println("Фильм с данным id не найден");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Не указан атрибут id");
        }
        catch (NumberFormatException e) {
            System.out.println("Id должен быть числом");
        }
    }
}