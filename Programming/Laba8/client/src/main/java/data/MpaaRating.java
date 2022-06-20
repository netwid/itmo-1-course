package data;

import client.WindowManager;

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
    public static MpaaRating input(MpaaRating mpaaRating) throws Exception {
        if (mpaaRating == null) {
            WindowManager.alert("Рейтинг не может быть null");
            throw new Exception();
        }
        return mpaaRating;
    }
}