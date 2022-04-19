package commands;

import data.Request;

/**
 * The type Exit command.
 */
public class ExitCommand implements Command {
    @Override
    public void execute(Request request) {
        System.exit(0);
    }
}
