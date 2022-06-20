package data;

import java.io.Serializable;

public class Yandex implements Serializable {
    public int id;
    public String login;

    public Yandex(int id, String login) {
        this.id = id;
        this.login = login;
    }
}
