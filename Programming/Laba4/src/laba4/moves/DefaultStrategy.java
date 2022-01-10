package laba4.moves;

import laba4.Place;

public class DefaultStrategy implements MoveStrategy {
    public String move(Place place) {
        return "подошёл к " + place.getName();
    }
}
