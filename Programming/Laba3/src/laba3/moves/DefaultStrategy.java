package laba3.moves;

import laba3.Place;

public class DefaultStrategy implements MoveStrategy {
    public String move(Place place) {
        return "подошёл к " + place.getName();
    }
}
