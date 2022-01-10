package laba4.characters;

import laba4.Place;
import laba4.exceptions.EmptyException;
import laba4.exceptions.NotEnoughSpaceException;
import laba4.food.Potato;
import laba4.Storable;
import laba4.Storagable;

import java.util.ArrayList;
import java.util.Objects;

public class Scooperfield extends Character {
    private final TopHat topHat;
    public final Pocket pocket;

    public Scooperfield(String name) {
        super(name);
        topHat = new TopHat();
        pocket = new Pocket();
    }

    private boolean checkPotatoField() {
        return this.place == Place.POTATO_FIELD;
    }

    public void collectTubers(Potato[] potatoes) {
        System.out.println(this.getName() + " собирает клубни");
        if (checkPotatoField()) {
            for (Potato p : potatoes) {
                while (!p.isEmpty()) {
                    topHat.put(p.getTuber());
                }
            }
        }
    }

    public static class TopHat implements Storagable {
        ArrayList<Storable> items;
        {
            items = new ArrayList<>();
        }

        public void put(Storable item) {
            System.out.println("В шляпу положен предмет");
            items.add(item);
        }

        public Storable pullOut() throws NotEnoughSpaceException {
            if (items.isEmpty())
                throw new NotEnoughSpaceException();

            System.out.println("Из шляпы вытащен предмет");
            return items.remove(items.size() - 1);
        }
    }

    public static class Pocket implements Storagable {
        Storable item;

        public void put(Storable item) throws NotEnoughSpaceException {
            if (item == null)
                throw new NotEnoughSpaceException();
            System.out.println("В карман положен предмет");
            this.item = item;
        }

        public Storable pullOut() throws EmptyException {
            if (this.item == null)
                throw new EmptyException();

            Storable temp = this.item;
            this.item = null;
            System.out.println("Из кармана вытащен предмет");
            return temp;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooperfield scooperfield = (Scooperfield) o;
        return alertness == scooperfield.alertness && Objects.equals(name, scooperfield.name) && place == scooperfield.place
                && topHat == scooperfield.topHat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place, alertness, topHat);
    }

    @Override
    public String toString() {
        return "laba4.characters.Scooperfield{" +
                "name='" + name + '\'' +
                ", place=" + place +
                ", alertness=" + alertness +
                ", topHat=" + topHat +
                '}';
    }
}


