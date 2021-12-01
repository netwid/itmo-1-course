package laba3.moves;

import laba3.Place;

public class PacingStrategy implements MoveStrategy {
    public String move(Place place) {
        return "зашагал к " + place.getName();
    }
}
