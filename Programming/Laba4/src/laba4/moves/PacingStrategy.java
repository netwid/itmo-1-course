package laba4.moves;

import laba4.Place;

public class PacingStrategy implements MoveStrategy {
    public String move(Place place) {
        return "зашагал к " + place.getName();
    }
}
