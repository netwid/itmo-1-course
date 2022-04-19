package data;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketAddress;

public class Request implements Serializable {
    public String command;
    public String[] args;
    public Serializable object;
    public SocketAddress client;

    public Request(String command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public Request() {

    }
}
