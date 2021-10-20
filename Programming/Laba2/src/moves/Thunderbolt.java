package moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Thunderbolt extends SpecialMove {
    private boolean isParalyzed;

    public Thunderbolt() {
        super(Type.ELECTRIC, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) {
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
