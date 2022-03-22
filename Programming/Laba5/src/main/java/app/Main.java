package app;

import java.util.Arrays;

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
        try {
            Invoker invoker = Invoker.getInstance(args[0], new CollectionManager());

            invoker.execute("load", args);

            while (true) {
                System.out.print("> ");
                String[] words = IO.get().trim().split("\\s+");
                String command = words[0];
                String[] argv = Arrays.copyOfRange(words, 1, words.length);
                invoker.execute(command, argv);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не указан обязательный аргумент - файл");
        }
    }
}