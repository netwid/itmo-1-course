package laba4.characters;

import laba4.food.Tuber;

public class Shorty extends Character {
    public Shorty(String name) {
        super(name);
    }

    public void boilTuber(Tuber tuber) {
        tuber.boil();
    }

    @Override
    public String toString() {
        return "laba4.characters.Shorty{" +
                "name='" + name + '\'' +
                ", place=" + place +
                ", alertness=" + alertness +
                '}';
    }
}
