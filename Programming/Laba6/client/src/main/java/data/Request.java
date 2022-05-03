package data;

import java.io.Serializable;
import java.net.SocketAddress;

public class Request implements Serializable {
    private static final long serialVersionUID = -1679248067145723066L;
    public String command;
    public String[] args;
    public Serializable object;
    public SocketAddress client;

    public Request(String command, String[] args, Serializable object) {
        this.command = command;
        this.args = args;
        this.object = object;
    }

    public Request(String command, String[] args) {
        this.command = command;
        this.args = args;
    }
}
