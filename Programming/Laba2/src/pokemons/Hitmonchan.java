package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Hitmonchan extends Pokemon {
    public Hitmonchan(String name, int level) {
        super(name, level);
        setStats(50, 105, 79, 35, 110, 76);
        setType(Type.FIGHTING);
        setMove(new IceBeam(), new FocusEnergy(), new Scald(), new SludgeBomb());
    }
}
