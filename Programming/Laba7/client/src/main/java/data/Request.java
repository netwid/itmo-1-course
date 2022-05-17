package data;

import java.io.Serializable;
import java.net.SocketAddress;

public class Request implements Serializable {
    private static final long serialVersionUID = -1679248067145723066L;
    public String command;
    public String[] args;
    public Serializable object;
    public SocketAddress client;
    public String login;
    public String password;

    public Request(String command, String[] args, String login, String password, Serializable object) {
        this.command = command;
        this.args = args;
        this.object = object;
        this.login = login;
        this.password = password;
    }

    public Request(String command, String[] args, String login, String password) {
        this.command = command;
        this.args = args;
        this.login = login;
        this.password = password;
    }
}
