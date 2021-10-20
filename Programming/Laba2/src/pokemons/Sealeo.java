package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Sealeo extends Pokemon {
    public Sealeo(String name, int level) {
        super(name, level);
        setStats(90, 60, 70, 75, 70, 45);
        setType(Type.ICE, Type.WATER);
        setMove(new WorkUp(), new WoodHammer(), new Sing(), new CalmMind());
    }
}
