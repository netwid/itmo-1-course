package server;

import data.Movie;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The type Collection manager.
 */
public class CollectionManager {
    /**
     * The Last id.
     */
    int lastId = 1;
    /**
     * The Movies.
     */
    HashSet<Movie> movies = new HashSet<>();
    /**
     * The Init time.
     */
    ZonedDateTime initTime = ZonedDateTime.now();
    DatabaseManager dm = new DatabaseManager();

    /**
     * Info about collection.
     */
    public String info() {
        return "Тип: " + movies.getClass() + "\n" +
               "Дата инициализации: " + initTime + "\n" +
               "Количество элементов: " + movies.size() + "\n";
    }

    public void load() {
        movies = new HashSet<>(dm.getAll());
    }

    /**
     * Add new movie.
     *
     * @param movie the movie
     */
    public void add(Movie movie) {
        int id = dm.add(movie);
        if (id > 0) {
            movie.setId(id);
            movies.add(movie);
        }
    }

    /**
     * Clear movies.
     */
    public void clear() {
        if (dm.clear())
            movies.clear();
    }

    /**
     * Get all movies.
     */
    public HashSet<Movie> getAll() {
        return movies;
    }

    /**
     * Update element by id.
     *
     * @param id       the id
     * @param newMovie the new movie
     * @return status
     */
    public boolean update(int id, Movie newMovie) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movies.remove(movie);
                newMovie.setId(id);
                movies.add(newMovie);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove movie by id.
     *
     * @param id the id
     * @return status
     */
    public boolean removeById(int id) {
        if (!dm.removeById(id))
            return false;
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movies.remove(movie);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove movie with lower length.
     *
     * @param length the length
     */
    public void removeLower(int length) {
        if (dm.removeLower(length))
            movies.removeIf(movie -> movie.getLength() < length);
    }


    /**
     * Contains passport boolean.
     *
     * @param passportId the passport id
     * @return the boolean
     */
    public boolean containsPassport(String passportId) {
        return movies
            .stream()
            .anyMatch(movie -> movie.getScreenwriter().getPassportID().equals(passportId));
    }

    /**
     * Filter movies list by lambda.
     *
     * @param filter the filter
     * @return the movies list
     */
    public List<Movie> filterMovies(Predicate<Movie> filter) {
        return movies
            .stream()
            .filter(filter)
            .collect(Collectors.toList());
    }

    /**
     * Print field descending genre.
     */
    public String printFieldDescendingGenre() {
        Map<Integer, String> genres = new TreeMap<>();
        for (Movie movie : movies) {
            genres.put(movie.getGenre().ordinal(), movie.getGenre().name());
        }

        StringBuilder sb = new StringBuilder("");
        for (String genre : genres.values()) {
            sb.append(genre).append('\n');
        }
        return String.valueOf(sb);
    }
}
