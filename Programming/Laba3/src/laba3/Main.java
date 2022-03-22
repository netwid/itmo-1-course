package laba3;

import laba3.moves.*;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scooperfield s = new Scooperfield("Скуперфильд");
        Watchman w = new Watchman("Охранник");
        Potato[] potatoes = {new Potato(), new Potato(), new Potato()};

        Stream.of(1, 2, 3).map(x -> x * x).forEach(System.out::println);

        s.setMoveStrategy(new GotOutStrategy());
        s.move(Place.TABLE);
        s.setMoveStrategy(new PacingStrategy());
        s.move(Place.WICKER);
        s.setMoveStrategy(new SneakingStrategy());
        s.move(Place.WICKER);
        s.setMoveStrategy(new DefaultStrategy());
        s.move(Place.POTATO_FIELD);

        s.collectTubers(potatoes);

        w.setMoveStrategy(new DefaultStrategy());
        w.move(Place.POTATO_FIELD);
        w.capture(s);
        s.react();
    }
}
