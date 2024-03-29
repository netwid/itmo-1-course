package server;

import commands.*;
import data.Request;
import data.Yandex;

import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
        ThreadPoolExecutor cachedPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        if (cachedPool.getActiveCount() < 1) {
            cachedPool.execute(() -> {
                request.command = request.command.toLowerCase(Locale.ROOT);
                try {
                    if (request.command.equals("register")) {
                        boolean result = AuthManager.register(request.login, request.password);
                        if (result) {
                            Server.sendObject(request.client, DatabaseManager.getInstance().getId(request.login));
                        } else {
                            Server.error(request.client, "Регистрация не удалась");
                        }
                        return;
                    }
                    if (request.command.equals("login")) {
                        boolean result = AuthManager.checkLogin(request.login, request.password);
                        if (result) {
                            Server.sendObject(request.client, DatabaseManager.getInstance().getId(request.login));
                        } else {
                            Server.error(request.client, "Неверные данные");
                        }
                        return;
                    }
                    if (request.command.equals("yandex")) {
                        String login = AuthManager.yandex(request.password);
                        if (login != null) {
                            Server.sendObject(request.client, new Yandex(DatabaseManager.getInstance().getId(login), login));
                        } else {
                            Server.error(request.client, "Токен не верифицирован");
                        }
                        return;
                    }
                    if (!AuthManager.checkLogin(request.login, request.password) && request.client != null) {
                        Server.error(request.client, "Требуется аутентификация");
                        return;
                    }
                    this.commands.get(request.command).execute(request);
                } catch (NullPointerException e) {
                    Server.error(request.client, "Команда не найдена\n");
                }
            });
        }
    }
}
