package app.commands;

import app.Invoker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The type Execute script command.
 */
public class ExecuteScriptCommand implements Command {
    private final HashMap<String, Boolean> executeFiles = new HashMap<>();

    @Override
    public void execute(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
            String line;
            Invoker invoker = Invoker.getInstance();

            if (executeFiles.containsKey(args[0])) {
                System.out.println("Вызов скриптов зациклен");
                return;
            }
            executeFiles.put(args[0], true);

            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                String command = words[0];
                String[] argv = Arrays.copyOfRange(words, 1, words.length);
                invoker.execute(command, argv);
            }

            executeFiles.clear();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Не передан аргумент путь до файла");
        }
        catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл");
        }
        catch (IOException e) {
            System.out.println("Ошибка при записи в файл. Недостаточно прав");
        }
    }
}
