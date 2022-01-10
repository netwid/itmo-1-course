package laba4.food;

import laba4.Storable;

public class Tuber implements Storable, Eatable {
    boolean isBoiled = false;

    public boolean isPoisoned() {
        return !isBoiled;
    }

    public void boil() {
        isBoiled = true;
    }
}
