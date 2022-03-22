package app;

import app.data.Movie;

import javax.json.bind.*;
import javax.json.bind.config.PropertyVisibilityStrategy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Predicate;

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
     */
    public void info() {
        System.out.println("Тип: " + movies.getClass());
        System.out.println("Дата инициализации: " + initTime);
        System.out.println("Количество элементов: " + movies.size());
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
     * Show movies.
     */
    public void show() {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
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
        for (Movie movie : movies) {
            if (movie.getPersonPassport().equals(passportId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Filter movies list by lambda.
     *
     * @param filter the filter
     * @return the movies list
     */
    public List<Movie> filterMovies(Predicate<Movie> filter) {
        List<Movie> res = new ArrayList<>();
        for (Movie movie : movies) {
            if (filter.test(movie)) {
                res.add(movie);
            }
        }
        return res;
    }

    /**
     * Print field descending genre.
     */
    public void printFieldDescendingGenre() {
        Map<Integer, String> genres = new TreeMap<>();
        for (Movie movie : movies) {
            genres.put(movie.getGenre().ordinal(), movie.getGenre().name());
        }
        genres.values().forEach(System.out::println);
    }
}
