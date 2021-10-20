package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Raticate extends Pokemon {
    public Raticate(String name, int level) {
        super(name, level);
        setStats(55, 81, 60, 50, 70, 97);
        setType(Type.WATER);
        setMove(new Thunderbolt(), new DoubleTeam(), new Discharge(), new Swagger());
    }
}
