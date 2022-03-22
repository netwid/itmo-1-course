package app.commands;

import app.CollectionManager;
import app.Invoker;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * The type Load command.
 */
public class LoadCommand implements Command {
    private final String fileName;
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Load command.
     *
     * @param fileName          the file name
     * @param collectionManager the collection manager
     */
    public LoadCommand(String fileName, CollectionManager collectionManager) {
        this.fileName = fileName;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName), StandardCharsets.UTF_8))) {
            String line = br.readLine();
            collectionManager.fromJson(line);
            return;
        }
        catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл");
        }
        catch (IOException e) {
            System.out.println("Ошибка при записи в файл. Недостаточно прав");
        }
        catch (NullPointerException e) {
            return;
        }
        Invoker.getInstance().execute("exit", new String[] {});
    }
}
