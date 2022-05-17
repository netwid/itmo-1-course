package server;

import data.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseManager {
    Connection conn;
    private static DatabaseManager instance;

    private DatabaseManager() {
        try {
            Scanner auth = new Scanner(new FileReader("db.txt"));
            String username = auth.nextLine().trim();
            String password = auth.nextLine().trim();

            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5454/studs", username, password);
            PreparedStatement ps = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS \"user\" (" +
                    "user_id SERIAL," +
                    "login VARCHAR(20) NOT NULL," +
                    "password VARCHAR(100) NOT NULL,"  +
                    "salt VARCHAR(10) NOT NULL" +
                ");" +
                "CREATE TABLE IF NOT EXISTS movie (" +
                    "movie_id SERIAL," +
                    "name VARCHAR(20) NOT NULL," +
                    "coordinates_x DOUBLE PRECISION NOT NULL,"  +
                    "coordinates_y INTEGER NOT NULL," +
                    "creation_date TIMESTAMP NOT NULL," +
                    "oscars_count BIGINT NOT NULL," +
                    "length INTEGER NOT NULL," +
                    "movie_genre VARCHAR(20)," +
                    "mpaa_rating VARCHAR(5) NOT NULL," +
                    "screenwriter_name VARCHAR(20) NOT NULL," +
                    "screenwriter_birthday TIMESTAMP," +
                    "screenwriter_height INTEGER NOT NULL," +
                    "screenwriter_weight DOUBLE PRECISION," +
                    "screenwriter_passport_id VARCHAR(41) NOT NULL UNIQUE" +
                ");"
            );
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Ошибка инициализации БД");
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл db.txt для аутенфикации");
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM movie");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("name"),
                    new Coordinates(rs.getDouble("coordinates_x"), rs.getInt("coordinates_y")),
                    rs.getDate("creation_date").toLocalDate(),
                    rs.getLong("oscars_count"),
                    rs.getInt("length"),
                    MovieGenre.valueOf(rs.getString("movie_genre")),
                    MpaaRating.valueOf(rs.getString("mpaa_rating")),
                    new Person(
                        rs.getString("screenwriter_name"),
                        rs.getTimestamp("screenwriter_birthday").toLocalDateTime(),
                        rs.getInt("screenwriter_height"),
                        rs.getDouble("screenwriter_weight"),
                        rs.getString("screenwriter_passport_id")
                    )
                );
                movies.add(movie);
            }
        } catch (SQLException e) {

        }
        return movies;
    }

    public int add(Movie movie) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO movie(name, coordinates_x, coordinates_y, " +
                "creation_date, oscars_count, length, movie_genre, mpaa_rating, screenwriter_name," +
                "screenwriter_birthday, screenwriter_height, screenwriter_weight, screenwriter_passport_id)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING movie_id");
            ps.setString(1, movie.getName());
            ps.setDouble(2, movie.getCoordinates().getX());
            ps.setInt(3, movie.getCoordinates().getY());
            ps.setDate(4, Date.valueOf(movie.getCreationDate()));
            ps.setLong(5, movie.getOscarsCount());
            ps.setInt(6, movie.getLength());
            ps.setString(7, movie.getGenre().name());
            ps.setString(8, movie.getMpaaRating().name());
            ps.setString(9, movie.getScreenwriter().getName());
            ps.setTimestamp(10, Timestamp.valueOf(movie.getScreenwriter().getBirthday()));
            ps.setInt(11, movie.getScreenwriter().getHeight());
            ps.setDouble(12, movie.getScreenwriter().getWeight());
            ps.setString(13, movie.getScreenwriter().getPassportID());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("movie_id");
        } catch (Exception e) {
            System.out.println("Ошибка добавления");
            return 0;
        }
    }

    public boolean update(int id, Movie movie) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO movie(id, name, coordinates_x, coordinates_y, " +
                    "creation_date, oscars_count, length, movie_genre, mpaa_rating, screenwriter_name," +
                    "screenwriter_birthday, screenwriter_height, screenwriter_weight, screenwriter_passport_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING movie_id");
            ps.setInt(1, id);
            ps.setString(2, movie.getName());
            ps.setDouble(3, movie.getCoordinates().getX());
            ps.setInt(4, movie.getCoordinates().getY());
            ps.setDate(5, Date.valueOf(movie.getCreationDate()));
            ps.setLong(6, movie.getOscarsCount());
            ps.setInt(7, movie.getLength());
            ps.setString(8, movie.getGenre().name());
            ps.setString(9, movie.getMpaaRating().name());
            ps.setString(10, movie.getScreenwriter().getName());
            ps.setTimestamp(11, Timestamp.valueOf(movie.getScreenwriter().getBirthday()));
            ps.setInt(12, movie.getScreenwriter().getHeight());
            ps.setDouble(13, movie.getScreenwriter().getWeight());
            ps.setString(14, movie.getScreenwriter().getPassportID());
            ResultSet rs = ps.executeQuery();
            rs.first();
            return rs.getInt("movie_id") == id;
        } catch (Exception e) {
            System.out.println("Ошибка обновления");
            return false;
        }
    }

    public boolean clear() {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM movie");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean removeById(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM movie WHERE movie_id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean removeLower(int length) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM movie WHERE length < ?");
            ps.setInt(1, length);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean existsUser(String login, String password) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"user\" WHERE login = ? AND password = ?");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean register(String login, String password) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"user\" WHERE login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO \"user\" (login, password, salt) VALUES (?, ?, ?)");
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, "");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }

    }
}
