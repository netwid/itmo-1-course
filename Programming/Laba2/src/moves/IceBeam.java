package moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class IceBeam extends SpecialMove {
    private boolean isFreezed;

    public IceBeam() {
        super(Type.ICE, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        /* We haven't abilities in lab */
        if (Math.random() <= 0.1) {
            Effect.freeze(p);
            isFreezed = true;
        }
    }

    @Override
    protected String describe() {
        if (isFreezed)
            return "атакует и замораживает противника";
        return "атакует";
    }
}
