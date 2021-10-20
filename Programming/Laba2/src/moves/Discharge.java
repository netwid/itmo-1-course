package moves;

import java.util.Arrays;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Discharge extends SpecialMove {
    private boolean isParalyzed;

    public Discharge() {
        super(Type.ELECTRIC, 80, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        /* We haven't abilities in lab */
        if (Math.random() <= 0.3) {
            Effect.paralyze(p);
            isParalyzed = true;
        }
    }

    @Override
    protected String describe() {
        if (isParalyzed)
            return "атакует и парализует противника";
        return "атакует";
    }
}
