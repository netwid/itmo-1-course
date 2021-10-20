package moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class SludgeBomb extends SpecialMove {
    private boolean isPoisoned;

    public SludgeBomb() {
        super(Type.POISON, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.3) {
            Effect.poison(p);
            isPoisoned = true;
        }
    }

    @Override
    protected String describe() {
        if (isPoisoned)
            return "атакует и отравляет противника";
        return "атакует";
    }
}
