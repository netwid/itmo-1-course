import java.util.Objects;

public class Scooperfield extends Character {
    TopHat topHat;

    Scooperfield(String name) {
        super(name);
        topHat = new TopHat();
    }

    private boolean checkPotatoField() {
        return this.place == Place.POTATO_FIELD;
    }

    public void collectTubers(Potato[] potatoes) {
        if (checkPotatoField())
            for (Potato p : potatoes)
                while (!p.isEmpty())
                    topHat.put(p.getTuber());
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
        return "Scooperfield{" +
                "name='" + name + '\'' +
                ", place=" + place +
                ", alertness=" + alertness +
                ", topHat=" + topHat +
                '}';
    }
}
