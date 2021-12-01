package laba3.moves;

import laba3.Place;

public class GotOutStrategy implements MoveStrategy {
    public String move(Place place) {
        return "выбрался из " + place.getName();
    }
}
