package laba4.food;

import laba4.characters.Character;

import java.lang.reflect.Field;

public class Drink {
    private final String name;

    public Drink(String name) {
        this.name = name;
    }

    public void drink(Character ch) {
        Class<? extends Character> character = ch.getClass();

        System.out.println(ch.getName() + " выпил " + this.name);

        try {
            Field warmth = character.getSuperclass().getDeclaredField("warmth");
            warmth.setAccessible(true);
            warmth.setInt(ch, warmth.getInt(ch) + 5);
        }
        catch (NoSuchFieldException ex) {
            System.out.println("Не удалось найти поле warmth");
        }
        catch (IllegalAccessException ex) {
            System.out.println("Не удалось получить доступ к полю warmth");
        }
    }
}
