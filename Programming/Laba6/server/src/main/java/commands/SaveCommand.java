package commands;

import data.Request;
import server.CollectionManager;
import server.Server;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The type Save command.
 */
public class SaveCommand implements Command {
    /**
     * The File name.
     */
    String fileName;
    private final CollectionManager collectionManager;

    /**
     * Instantiates a new Save command.
     *
     * @param fileName          the file name
     * @param collectionManager the collection manager
     */
    public SaveCommand(String fileName, CollectionManager collectionManager) {
        this.fileName = fileName;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(Request request) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = collectionManager.getJson().getBytes();
            bos.write(buffer);
            Server.print(request.client, "Коллекция сохранена в " + fileName + "\n");
        }
        catch (FileNotFoundException e) {
            Server.print(request.client, "Не удалось найти файл\n");
        }
        catch (IOException e) {
            Server.print(request.client, "Ошибка при записи в файл. Недостаточно прав\n");
        }
    }
}
