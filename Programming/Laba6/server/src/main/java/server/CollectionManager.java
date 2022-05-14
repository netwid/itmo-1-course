package server;

import data.Movie;

import javax.json.bind.*;
import javax.json.bind.config.PropertyVisibilityStrategy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

    /**
     * Info about collection.
     * @return
     */
    public String info() {
        return "Тип: " + movies.getClass() + "\n" +
               "Дата инициализации: " + initTime + "\n" +
               "Количество элементов: " + movies.size() + "\n";
    }

    /**
     * Add new movie.
     *
     * @param movie the movie
     */
    public void add(Movie movie) {
        movies.add(movie);
    }

    /**
     * Clear movies.
     */
    public void clear() {
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
        movies.removeIf(movie -> movie.getLength() < length);
    }

    /**
     * Export collection to json.
     *
     * @return the json string
     */
    public String getJson() {
        Jsonb jsonb = JsonbBuilder.newBuilder().withConfig(
            new JsonbConfig().withPropertyVisibilityStrategy(new PropertyVisibilityStrategy() {
                @Override
                public boolean isVisible(Field field) {
                    return true;
                }

                @Override
                public boolean isVisible(Method method) {
                    return true;
                }
            })
        ).build();
        return jsonb.toJson(movies);
    }

    /**
     * Import collection from json.
     *
     * @param json the json string
     */
    public void fromJson(String json) {
        Jsonb jsonb = JsonbBuilder.newBuilder().withConfig(
                new JsonbConfig().withPropertyVisibilityStrategy(new PropertyVisibilityStrategy() {
                    @Override
                    public boolean isVisible(Field field) {
                        return true;
                    }

                    @Override
                    public boolean isVisible(Method method) {
                        return true;
                    }
                })
        ).build();
        movies = jsonb.fromJson(json, new HashSet<Movie>(){}.getClass().getGenericSuperclass());
    }

    /**
     * Gets id for new element.
     *
     * @return the id
     */
    public int getId() {
        return lastId++;
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
            .anyMatch(movie -> movie.getPersonPassport().equals(passportId));
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
