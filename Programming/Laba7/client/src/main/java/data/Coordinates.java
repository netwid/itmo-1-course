package data;

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
     * Input coordinates.
     *
     * @return the coordinates
     */
    public static Coordinates input() {
        double x = inputX();
        Integer y = inputY();

        return new Coordinates(x, y);
    }

    /**
     * Input x coordinate.
     *
     * @return the double
     */
    private static double inputX() {
        try {
            System.out.print("Введите координаты x: ");
            return Double.parseDouble(IO.get());
        }
        catch (NumberFormatException e) {
            System.out.println("Значение должно быть числом");
            return inputX();
        }
    }

    /**
     * Input y coordinate.
     *
     * @return the int
     */
    private static Integer inputY() {
        try {
            System.out.print("Введите координаты y: ");
            int y = Integer.parseInt(IO.get());
            if (y > 739)
                throw new Exception("Максимальное значение для y - 739");
            return y;
        }
        catch (NumberFormatException e) {
            System.out.println("Значение должно быть целым числом");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return inputY();
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