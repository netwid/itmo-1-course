package app;

import java.util.Scanner;

/**
 * The type Io.
 */
public class IO {
    /**
     * Print string to user.
     *
     * @param out the out string
     */
    public static void print(String out) {
        System.out.print(out);
    }

    /**
     * Get string from user.
     *
     * @return the string
     */
    public static String get() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
