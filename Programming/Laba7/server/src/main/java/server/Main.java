package server;

import data.Request;

import java.io.IOException;
import java.util.Scanner;

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
            System.out.print("> ");

            while (true) {
                Request request = Server.receive();
                if (request != null) {
                    invoker.execute(request);
                }

                if (System.in.available() > 0) {
                    invoker.execute(new Request(new Scanner(System.in).nextLine(), new String[] {}, "", ""));
                    System.out.print("> ");
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не указан обязательный аргумент - файл");
        } catch (IOException e) {
            System.out.println("Ошибка буфера ввода");
        }
    }
}