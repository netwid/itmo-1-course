package moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Scald extends SpecialMove {
    private boolean isBurned;

    public Scald() {
        super(Type.WATER, 80, 100);
    }

    @Override
    public void applyOppDamage(Pokemon p, double damage) {
        /* We haven't abilities in lab */
        if (Math.random() <= 0.3) {
            Effect.burn(p);
            isBurned = true;
        }
    }

    @Override
    protected String describe() {
        if (isBurned)
            return "атакует и поджигает противника";
        return "атакует";
    }
}
