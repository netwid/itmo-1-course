public class Main {
    public static void main(String[] args) {
        Scooperfield s = new Scooperfield("Скуперфильд");
        Watchman w = new Watchman("Охранник");
        Potato[] potatoes = {new Potato(), new Potato(), new Potato()};

        s.move(Place.TABLE);
        s.move(Place.WICKER);
        s.move(Place.POTATO_FIELD);

        s.collectTubers(potatoes);

        w.move(Place.POTATO_FIELD);
        w.capture(s);
        System.out.println(s.react());
    }
}
