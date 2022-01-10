package laba4.moves;

import laba4.Place;

public class GotOutStrategy implements MoveStrategy {
    public String move(Place place) {
        return "выбрался из " + place.getName();
    }
}
