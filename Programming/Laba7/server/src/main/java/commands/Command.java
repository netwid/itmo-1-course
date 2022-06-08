package commands;

import data.Request;

/**
 * The interface commands.Command.
 */
public interface Command {
    /**
     * Main method to execute command.
     *
     * @param request the request with data for command
     */
    void execute(Request request);
}
