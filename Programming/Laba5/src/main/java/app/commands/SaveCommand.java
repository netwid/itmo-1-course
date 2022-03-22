package app.commands;

import app.CollectionManager;

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
    public void execute(String[] args) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = collectionManager.getJson().getBytes();
            bos.write(buffer);
            System.out.println("Коллекция сохранена в " + fileName);
        }
        catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл");
        }
        catch (IOException e) {
            System.out.println("Ошибка при записи в файл. Недостаточно прав");
        }
    }
}
