package pokemons;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import moves.*;

public class Articuno extends Pokemon {
    public Articuno(String name, int level) {
        super(name, level);
        setStats(90, 85, 100, 95, 125, 85);
        setType(Type.ICE, Type.FLYING);
        setMove(new IceBeam(), new FocusEnergy(), new Scald());
    }
}
