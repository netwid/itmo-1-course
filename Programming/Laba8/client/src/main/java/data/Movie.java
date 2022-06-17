package data;

import client.WindowManager;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The type data.Movie.
 */
public class Movie implements Serializable {
    private static final long serialVersionUID = 3932727885621424695L;
    private int id;            // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name;             // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private LocalDate creationDate;  // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long oscarsCount;        // Значение поля должно быть больше 0, Поле не может быть null
    private int length;              // Значение поля должно быть больше 0
    private MovieGenre genre;        // Поле может быть null
    private MpaaRating mpaaRating;   // Поле не может быть null
    private Person screenwriter;

    public Movie(int id, String name, Coordinates coordinates, LocalDate creationDate, long oscarsCount, int length,
                 MovieGenre genre, MpaaRating mpaaRating, Person screenwriter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.length = length;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.screenwriter = screenwriter;
    }

    /**
     * Instantiates a new data.Movie.
     */
    public Movie() {
    }

    /**
     * Input name string.
     *
     * @return the name
     */
    public static String inputName(String name) {
        if (name.isEmpty()) {
            WindowManager.alert("Название не может быть пустым");
        }
        return name;
    }

    /**
     * Input oscars count.
     *
     * @return the oscars count
     */
    public static long inputOscars(String oscars) throws Exception {
        try {
            long oscarsCount = Long.parseLong(oscars);
            if (oscarsCount <= 0)
                throw new Exception("Количество оскаров должно быть больше нуля");
            return oscarsCount;
        }
        catch (NumberFormatException e) {
            WindowManager.alert("Количество оскаров должно быть целым числом");
        } catch (Exception e) {
            WindowManager.alert(e.getMessage());
        }
        throw new Exception();
    }

    /**
     * Input length.
     *
     * @return the length
     */
    public static int inputLength(String len) throws Exception {
        try {
            int length = Integer.parseInt(len);
            if (length <= 0)
                throw new Exception("Длина должна быть больше нуля");
            return length;
        }
        catch (NumberFormatException e) {
            WindowManager.alert("Длина должна быть целым числом");
        } catch (Exception e) {
            WindowManager.alert(e.getMessage());
        }
        throw new Exception("Длина должна быть больше нуля");
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Gets length.
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * Gets person.
     *
     * @return the person
     */
    public Person getScreenwriter() {
        return screenwriter;
    }

    /**
     * Gets mpaa rating.
     *
     * @return the mpaa rating
     */
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public MovieGenre getGenre() {
        return genre;
    }

    public double coordinatesTo(Coordinates coordinates) {
        return Math.sqrt(Math.pow(this.coordinates.getX() - coordinates.getX(), 2) +
                         Math.pow(this.coordinates.getY() - coordinates.getY(), 2));
    }

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    public Movie changeCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    @Override
    public String toString() {
        return "data.Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", oscarsCount=" + oscarsCount +
                ", length=" + length +
                ", genre=" + genre +
                ", mpaaRating=" + mpaaRating +
                ", screenwriter=" + screenwriter +
                '}';
    }
}