import pokemons.*;
import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();

        b.addAlly(new Articuno("Lorem", 47));
        b.addAlly(new Butterfree("Ipsum", 30));
        b.addAlly(new Hitmonchan("dolor", 20));

        b.addFoe(new Horsea("sit", 30));
        b.addFoe(new Raticate("amet", 47));
        b.addFoe(new Sealeo("consectetur", 20));

        b.go();
    }
}
