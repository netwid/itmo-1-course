package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Butterfree extends Pokemon {
    public Butterfree(String name, int level) {
        super(name, level);
        setStats(60, 45, 50, 90, 80, 70);
        setType(Type.BUG, Type.FLYING);
        setMove(new Thunderbolt(), new DoubleTeam(), new Discharge());
    }
}
