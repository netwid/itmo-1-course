package laba4;

import laba4.characters.Scooperfield;
import laba4.characters.Shorty;
import laba4.characters.Watchman;
import laba4.exceptions.NotEnoughSpaceException;
import laba4.food.Drink;
import laba4.food.Eatable;
import laba4.food.Potato;
import laba4.food.Tuber;
import laba4.moves.DefaultStrategy;
import laba4.moves.GotOutStrategy;
import laba4.moves.PacingStrategy;
import laba4.moves.SneakingStrategy;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scooperfield s = new Scooperfield("Скуперфильд");
        Watchman w = new Watchman("Охранник");
        Potato[] potatoes = {new Potato(), new Potato(), new Potato()};
        HashMap<String, Shorty> shorties = getShorties();
        try {
            s.pocket.put(new Tuber());
        }
        catch (NotEnoughSpaceException e) {
            System.out.println("Не удалось положить клубень в карман");
        }

        s.drink(new Drink("Кипяток"));
        s.react();

        s.say("Я, братцы, нашел тут какие-то штучки. Может быть, их можно есть?");
        shorties.forEach((name, obj) -> obj.say("АХАХАХА"));
        shorties.forEach((name, obj) -> obj.say("Это же картофель! Его можно испечь"));
        s.say("А вы умеете?");
        shorties.get("Мизинчик").say("Еще бы не уметь!");

        Eatable tuber = (Eatable) s.pocket.pullOut();
        if (Math.random() < 0.3)
            s.eat(tuber);
        s.move(Place.BONFIRE);
        s.say("Так вы, братцы, пеките, а я принесу еще");

        s.setMoveStrategy(new GotOutStrategy());
        s.move(Place.TABLE);
        s.setMoveStrategy(new PacingStrategy());
        s.move(Place.WICKER);
        s.setMoveStrategy(new SneakingStrategy());
        s.move(Place.WICKER);
        s.setMoveStrategy(new DefaultStrategy());
        s.move(Place.POTATO_FIELD);

        s.collectTubers(potatoes);

        w.move(Place.POTATO_FIELD);
        w.capture(s);
        s.react();
    }

    public static HashMap<String, Shorty> getShorties() {
        HashMap<String, Shorty> map = new HashMap<>();

        map.put("Мизинчик", new Shorty("Мизинчик"));
        String[] names = {"Указательчик", "Безымянчик", "Большинчик", "Среднчик"};
        for (int i = 0; i < 2; i++) {
            String name = names[(int)(Math.random() * names.length)];
            map.put(name, new Shorty(name));
        }

        return map;
    }
}
