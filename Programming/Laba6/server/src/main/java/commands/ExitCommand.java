package commands;

import data.Request;
import server.Server;

public class ExitCommand implements Command {
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

        System.exit(0);
    }
}
