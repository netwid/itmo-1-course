package server;

import commands.*;
import data.Request;

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
     *  @param collectionManager the collection manager
     */
    public Invoker(CollectionManager collectionManager) {
        commands.put("load", new LoadCommand(collectionManager));
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commands.put("count_less_than_mpaa_rating", new CountLessThanMpaaRatingCommand(collectionManager));
        commands.put("filter_greater_than_mpaa_rating", new FilterGreaterThanMpaaRatingCommand(collectionManager));
        commands.put("print_field_descending_genre", new PrintFieldDescendingGenre(collectionManager));
        commands.put("check_passport", new CheckPassportCommand(collectionManager));
        commands.put("exit", new ExitCommand());
    }

    public static Invoker getInstance(CollectionManager collectionManager) {
        if (invoker == null)
            invoker = new Invoker(collectionManager);
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
     * @param request request with data for command
     */
    public void execute(Request request) {
        request.command = request.command.toLowerCase(Locale.ROOT);
        try {
            if (request.command.equals("register")) {
                boolean result = AuthManager.register(request.login, request.password);
                Server.print(request.client, result ? "Регистрация успешна" : "Error");
                return;
            }
            if (request.command.equals("login")) {
                boolean result = AuthManager.checkLogin(request.login, request.password);
                Server.print(request.client, result ? "Аутенфицировано" : "Error");
                return;
            }
            if (!AuthManager.checkLogin(request.login, request.password) && request.client != null) {
                Server.print(request.client, "Требуется аутенфикация");
                return;
            }
            this.commands.get(request.command).execute(request);
        }
        catch (NullPointerException e) {
            Server.print(request.client, "Команда не найдена\n");
        }
    }
}
