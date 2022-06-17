package data;

import client.WindowManager;

import java.io.Serializable;

/**
 * The type data.Coordinates.
 */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = -1586192050836290024L;
    private double x;
    private Integer y; // Максимальное значение поля: 739, Поле не может быть null

    public Coordinates(double x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new data.Coordinates.
     */
    public Coordinates() {

    }

    /**
     * Input x coordinate.
     *
     * @return the double
     */
    public static double inputX(String x) throws Exception {
        try {
            return Double.parseDouble(x);
        }
        catch (NumberFormatException e) {
            WindowManager.alert("Значение должно быть числом");
        }
        throw new Exception();
    }

    /**
     * Input y coordinate.
     *
     * @return the int
     */
    public static Integer inputY(String y_) throws Exception {
        try {
            int y = Integer.parseInt(y_);
            if (y > 739)
                throw new Exception("Максимальное значение для y - 739");
            return y;
        }
        catch (NumberFormatException e) {
            WindowManager.alert("Значение должно быть целым числом");
        } catch (Exception e) {
            WindowManager.alert(e.getMessage());
        }
        throw new Exception();
    }

    public double getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "data.Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}