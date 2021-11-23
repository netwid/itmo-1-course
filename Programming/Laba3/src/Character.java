import java.sql.SQLOutput;
import java.util.Objects;

public class Character {
    String name;
    protected Place place;
    protected int alertness;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void move(Place place) {
        this.place = place;
        System.out.println(this.getName() + " пошёл к " + place.getName());
    }

    public String react() {

        if (alertness > 5) {
            this.place = Place.ASYLUM;
            return this.getName() + " испугался и убежал";
        }
        return "";
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
        return "Character{" +
                "name='" + name + '\'' +
                ", place=" + place +
                ", alertness=" + alertness +
                '}';
    }
}
