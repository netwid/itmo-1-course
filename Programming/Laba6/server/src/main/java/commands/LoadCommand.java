package commands;

import data.Request;
import server.CollectionManager;
import server.Invoker;
import server.Server;

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
    public void execute(Request request) {
        String errorMsg;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName), StandardCharsets.UTF_8))) {
            String line = br.readLine();
            collectionManager.fromJson(line);
            return;
        }
        catch (FileNotFoundException e) {
            errorMsg = "Не удалось найти файл\n";
        }
        catch (IOException e) {
            errorMsg = "Ошибка при записи в файл. Недостаточно прав\n";
        }
        catch (NullPointerException e) {
            return;
        }
        if (request.client == null) {
            System.out.println(errorMsg);
            System.exit(0);
        }
        else {
            Server.print(request.client, errorMsg);
        }
    }
}
