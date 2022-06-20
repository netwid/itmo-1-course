package commands;

import data.Request;
import server.Invoker;
import server.Server;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The type Execute script command.
 */
public class ExecuteScriptCommand implements Command {
    private final HashMap<String, Boolean> executeFiles = new HashMap<>();

    @Override
    public void execute(Request request) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(request.args[0]), StandardCharsets.UTF_8));
            String line;
            Invoker invoker = Invoker.getInstance();

            if (executeFiles.containsKey(request.args[0])) {
                Server.error(request.client, "Вызов скриптов зациклен\n");
                return;
            }
            executeFiles.put(request.args[0], true);

            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                String command = words[0];
                String[] argv = Arrays.copyOfRange(words, 1, words.length);
                invoker.execute(new Request(command, argv, "", ""));
            }

            executeFiles.clear();
        }
        catch (IndexOutOfBoundsException e) {
            Server.error(request.client, "Не передан аргумент путь до файла\n");
        }
        catch (FileNotFoundException e) {
            Server.error(request.client, "Не удалось найти файл\n");
        }
        catch (IOException e) {
            Server.error(request.client, "Ошибка при записи в файл. Недостаточно прав\n");
        }
    }
}