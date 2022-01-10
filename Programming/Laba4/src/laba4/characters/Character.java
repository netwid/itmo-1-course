package laba4.characters;

import laba4.exceptions.PoisonedRuntimeException;
import laba4.food.Drink;
import laba4.Place;
import laba4.food.Eatable;
import laba4.moves.DefaultStrategy;
import laba4.moves.MoveStrategy;

import java.util.Objects;

public class Character {
    String name;
    protected Place place;
    protected int alertness;
    protected int warmth;
    protected int hunger = 5;
    protected MoveStrategy moveStrategy;

    public Character(String name) {
        this.name = name;
        this.setMoveStrategy(new DefaultStrategy());
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

    public void drink(Drink drink) {
        drink.drink(this);
    }

    public void eat(Eatable food) {
        if (food.isPoisoned())
            throw new PoisonedRuntimeException(this.name);
        this.hunger = 0;
    }

    public void react() {
        if (alertness >= 5) {
            this.place = Place.ASYLUM;
            System.out.println(this.getName() + " испугался и убежал");
            alertness = 0;
        }

        if (warmth >= 5) {
            System.out.println(this.getName() + " почувствовал тепло");
            warmth = 0;
        }

        if (hunger >= 5) {
            System.out.println(this.getName() + " чувствует голод");
        }
    }

    public void say(String phrase) {
        class Phrase {
            final String phrase;

            Phrase(String phrase) {
                this.phrase = phrase;
            }

            public String say() {
                if (phrase.charAt(phrase.length() - 1) == '!')
                    return Character.this.name + " воскликнул: " + phrase;
                if (phrase.charAt(phrase.length() - 1) == '?')
                    return Character.this.name + " спросил: " + phrase;

                return Character.this.name + " сказал: " + phrase;
            }
        }

        System.out.println(new Phrase(phrase).say());
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
        return "laba4.characters.Character{" +
                "name='" + name + '\'' +
                ", place=" + place +
                ", alertness=" + alertness +
                '}';
    }
}
