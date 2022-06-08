package data;

import java.io.Serializable;

/**
 * The enum data.Movie genre.
 */
public enum MovieGenre implements Serializable {
    /**
     * Action movie genre.
     */
    ACTION,
    /**
     * Drama movie genre.
     */
    DRAMA,
    /**
     * Thriller movie genre.
     */
    THRILLER,
    /**
     * Fantasy movie genre.
     */
    FANTASY,
    /**
     * Science fiction movie genre.
     */
    SCIENCE_FICTION;

    /**
     * Input movie genre.
     *
     * @return the movie genre
     */
    public static MovieGenre input() {
        int cnt = 1;
        System.out.println("Выберите жанр");
        for (MovieGenre item : MovieGenre.values()) {
            System.out.println(item.name() + " [" + cnt++ + "]");
        }

        try {
            String in = IO.get();
            if (in.equals(""))
                return null;
            int num = Integer.parseInt(in);
            return MovieGenre.values()[num - 1];
        }
        catch (NumberFormatException e) {
            System.out.println("Введите число");
            return MovieGenre.input();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Число не попадает в указанный диапазон");
            return MovieGenre.input();
        }
    }
}