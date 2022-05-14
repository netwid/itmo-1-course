package commands;

import data.Request;

public class ExitCommand implements Command {
    @Override
    public void execute(Request request) {
        System.exit(0);
    }
}
