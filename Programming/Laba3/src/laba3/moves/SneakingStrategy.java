package laba3.moves;

import laba3.Place;

public class SneakingStrategy implements MoveStrategy {
    public String move(Place place) {
        return "пробрался через " + place.getName();
    }
}
