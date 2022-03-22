package app;

import app.commands.*;

import java.util.HashMap;
import java.util.Locale;

/**
 * The type Invoker.
 */
public class Invoker {
    private final HashMap<String, Command> commands = new HashMap<>();
    private static Invoker invoker;

    /**
     * Initialize commands.
     *
     * @param fileName          the file name
     * @param collectionManager the collection manager
     */
    public Invoker(String fileName, CollectionManager collectionManager) {
        commands.put("load", new LoadCommand(fileName, collectionManager));
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("save", new SaveCommand(fileName, collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("exit", new ExitCommand());
        commands.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commands.put("count_less_than_mpaa_rating", new CountLessThanMpaaRatingCommand(collectionManager));
        commands.put("filter_greater_than_mpaa_rating", new FilterGreaterThanMpaaRatingCommand(collectionManager));
        commands.put("print_field_descending_genre", new PrintFieldDescendingGenre(collectionManager));
    }

    public static Invoker getInstance(String fileName, CollectionManager collectionManager) {
        if (invoker == null)
            invoker = new Invoker(fileName, collectionManager);
        return invoker;
    }

    public static Invoker getInstance() {
        if (invoker == null)
            throw new RuntimeException("Invoker не инициализирован");
        return invoker;
    }

    /**
     * Execute command.
     *
     * @param command the command
     * @param args    the command args
     */
    public void execute(String command, String[] args) {
        command = command.toLowerCase(Locale.ROOT);
        try {
            this.commands.get(command).execute(args);
        }
        catch (NullPointerException e) {
            System.out.println("Команда не найдена");
        }
    }
}
