package server;

import data.Request;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
            Invoker invoker = Invoker.getInstance(new CollectionManager());
            invoker.execute(new Request("load", args, "", ""));

            ThreadPoolExecutor cachedPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            while (true) {
                if (cachedPool.getActiveCount() < 4) {
                    cachedPool.execute(() -> {
                        Request request = Server.receive();
                        if (request != null) {
                            invoker.execute(request);
                        }
                    });
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не указан обязательный аргумент - файл");
        }
    }
}