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
        if (!request.client.toString().substring(1, request.client.toString().indexOf(':')).equals("127.0.0.1")) {
            Server.print(request.client, "Вы не можете сохранить файл не с сервера\n");
            return;
        }

        if (request.args.length < 1 || !request.args[0].equals("SuperPassword")) {
            Server.print(request.client, "Не передан мастер-пароль или введён неверный\n");
            return;
        }

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
