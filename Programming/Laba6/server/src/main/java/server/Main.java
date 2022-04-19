package server;

import data.Request;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting server module");

        try {
            Invoker invoker = Invoker.getInstance(args[0], new CollectionManager());
            invoker.execute(new Request("load", args, null));

            while (true) {
                Request request = Server.receive();
                invoker.execute(request);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не указан обязательный аргумент - файл");
        }
    }
}