package data;

import client.Client;

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

    private Movie(int id, String name, Coordinates coordinates, long oscarsCount, int length, MovieGenre genre,
                 MpaaRating mpaaRating, Person screenwriter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
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
     * Input movie.
     *
     * @return the movie
     */
    public static Movie input() {
        int id = Client.getId();
        String name = inputName();
        Coordinates coordinates = Coordinates.input();
        long oscarsCount = inputOscars();
        int length = inputLength();
        MovieGenre movieGenre = MovieGenre.input();
        MpaaRating mpaaRating = MpaaRating.input();
        System.out.println("Введите информацию о режиссёре");
        Person person = Person.input();

        return new Movie(id, name, coordinates, oscarsCount, length, movieGenre, mpaaRating, person);
    }

    /**
     * Input name string.
     *
     * @return the name
     */
    public static String inputName() {
        System.out.print("Введите название: ");
        String name = IO.get();
        if (name.isEmpty()) {
            System.out.println("Название не может быть пустым");
            return inputName();
        }
        return name;
    }

    /**
     * Input oscars count.
     *
     * @return the oscars count
     */
    public static long inputOscars() {
        try {
            System.out.print("Введите количество оскаров: ");
            long oscarsCount = Long.parseLong(IO.get());
            if (oscarsCount <= 0)
                throw new Exception("Количество оскаров должно быть больше нуля");
            return oscarsCount;
        }
        catch (NumberFormatException e) {
            System.out.println("Количество оскаров должно быть целым числом");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return inputOscars();
    }

    /**
     * Input length.
     *
     * @return the length
     */
    public static int inputLength() {
        try {
            System.out.print("Введите длину: ");
            int length = Integer.parseInt(IO.get());
            if (length <= 0)
                throw new Exception("Длина должна быть больше нуля");
            return length;
        }
        catch (NumberFormatException e) {
            System.out.println("Длина должна быть целым числом");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return inputLength();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
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
     * Gets person passport.
     *
     * @return the person passport
     */
    public String getPersonPassport() {
        return screenwriter.getPassportID();
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