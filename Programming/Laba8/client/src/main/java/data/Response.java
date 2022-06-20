package data;

import java.io.Serializable;

public class Response implements Serializable {
    public boolean success;
    public String message;
    public String command;
    public Serializable object;
}
