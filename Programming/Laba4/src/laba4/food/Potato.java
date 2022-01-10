package laba4.food;

import java.util.ArrayList;
import java.util.Objects;

public class Potato {
    private final Root root;
    private final ArrayList<Tuber> tubers;
    {
        root = new Root();
        tubers = new ArrayList<Tuber> ();
    }

    public Potato() {
        for (int i = 0; i < 2 + (int)(Math.random() * 6); i++)
            tubers.add(new Tuber());
    }

    public boolean isEmpty() {
        return tubers.isEmpty();
    }

    public Tuber getTuber() {
        System.out.println("Клубень отделён");
        return tubers.remove(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Potato potato = (Potato) o;
        return Objects.equals(root, potato.root) && Objects.equals(tubers, potato.tubers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, tubers);
    }

    @Override
    public String toString() {
        return "laba4.food.Potato{" +
                "root=" + root +
                ", tubers=" + tubers +
                '}';
    }
}
