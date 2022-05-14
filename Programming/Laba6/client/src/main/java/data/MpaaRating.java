package data;

import java.io.Serializable;

/**
 * The enum Mpaa rating.
 */
public enum MpaaRating implements Serializable {
    /**
     * G mpaa rating.
     */
    G,
    /**
     * Pg 13 mpaa rating.
     */
    PG_13,
    /**
     * R mpaa rating.
     */
    R,
    /**
     * Nc 17 mpaa rating.
     */
    NC_17;

    /**
     * Compare ratings int.
     *
     * @param mpaaRating the mpaa rating
     * @return the int
     */
    public int compareRatings(MpaaRating mpaaRating) {
        return Integer.compare(this.ordinal(), mpaaRating.ordinal());
    }

    /**
     * Input mpaa rating.
     *
     * @return the mpaa rating
     */
    public static MpaaRating input() {
        int cnt = 1;
        System.out.println("Выберите возрастной рейтинг");
        for (MpaaRating item : MpaaRating.values()) {
            System.out.println(item.name() + " [" + cnt++ + "]");
        }

        try {
            int num = Integer.parseInt(IO.get());
            return MpaaRating.values()[num - 1];
        }
        catch (NumberFormatException e) {
            System.out.println("Введите число");
            return MpaaRating.input();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Число не попадает в указанный диапазон");
            return MpaaRating.input();
        }
    }
}