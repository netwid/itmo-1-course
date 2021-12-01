package laba3;

import laba3.moves.MoveStrategy;

import java.util.Objects;

public class Character {
    String name;
    protected Place place;
    protected int alertness;
    protected MoveStrategy moveStrategy;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void move(Place place) {
        this.place = place;
        System.out.println(this.getName() + " " + moveStrategy.move(place));
    }

    public void react() {
        if (alertness > 5) {
            this.place = Place.ASYLUM;
            System.out.println(this.getName() + " испугался и убежал");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return alertness == character.alertness && Objects.equals(name, character.name) && place == character.place;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place, alertness);
    }

    @Override
    public String toString() {
        return "laba3.Character{" +
                "name='" + name + '\'' +
                ", place=" + place +
                ", alertness=" + alertness +
                '}';
    }
}
