package data;

import java.io.Serializable;

public class Response implements Serializable {
    public String toPrint;
    public Class<? extends Serializable> toInput;
    public String command;
}
