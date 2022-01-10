package laba4.moves;

import laba4.Place;

public class SneakingStrategy implements MoveStrategy {
    public String move(Place place) {
        return "пробрался через " + place.getName();
    }
}
