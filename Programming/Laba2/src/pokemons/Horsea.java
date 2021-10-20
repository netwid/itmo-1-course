package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Horsea extends Pokemon {
    public Horsea(String name, int level) {
        super(name, level);
        setStats(30, 40, 70, 70, 25, 60);
        setType(Type.WATER);
        setMove(new Thunderbolt(), new DoubleTeam());
    }
}
