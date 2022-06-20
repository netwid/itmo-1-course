package data;

import client.Client;
import client.WindowManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The type data.Person.
 */
public class Person implements Serializable {
    private String name;            // Поле не может быть null, Строка не может быть пустой
    private LocalDateTime birthday; // Поле может быть null
    private int height;             // Значение поля должно быть больше 0
    private Double weight;          // Поле может быть null, Значение поля должно быть больше 0
    private String passportID;      // Длина строки не должна быть больше 41, Значение этого поля должно быть уникальным, Поле не может быть null

    public Person(String name, LocalDateTime birthday, int height, Double weight, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }

    /**
     * Instantiates a new data.Person.
     */
    public Person() {

    }

    /**
     * Input name.
     *
     * @return the name
     */
    public static String inputName(String name) throws Exception {
        if (name.isEmpty()) {
            WindowManager.alert("Имя не может быть пустым");
            throw new Exception();
        }
        return name;
    }

    /**
     * Input birthday.
     *
     * @return the birthday
     */
    public static LocalDateTime inputBirthday() {
        try {
            String in = IO.get();
            if (in.equals(""))
                return null;
            return LocalDate.parse(in, DateTimeFormatter.ISO_DATE).atStartOfDay();
        }
        catch (DateTimeParseException e) {
            System.out.println("Введите дату в правильном формате");
        }
        return inputBirthday();
    }

    /**
     * Input height.
     *
     * @return the height
     */
    public static int inputHeight(String height_) throws Exception {
        try {
            int height = Integer.parseInt(height_);
            if (height <= 0)
                throw new Exception("Рост должен быть больше нуля");
            return height;
        }
        catch (NumberFormatException e) {
            WindowManager.alert("Рост должен быть числом");
        } catch (Exception e) {
            WindowManager.alert(e.getMessage());
        }
        throw new Exception();
    }

    /**
     * Input weight.
     *
     * @return the weight
     */
    public static Double inputWeight(String in) throws Exception {
        try {
            if (in.equals(""))
                return null;
            double weight = Double.parseDouble(in);
            if (weight <= 0)
                throw new Exception("Вес должен быть больше нуля");
            return weight;
        }
        catch (NumberFormatException e) {
            WindowManager.alert("Вес должен быть числом");
        } catch (Exception e) {
            WindowManager.alert(e.getMessage());
        }
        throw new Exception();
    }

    /**
     * Input passport id.
     *
     * @return the passport id
     */
    public static String inputPassportId(String passportID) throws Exception {
        if (passportID.length() > 41) {
            WindowManager.alert("Значение паспорта не должно превышать 41 символа");
            throw new Exception();
        }
        return passportID;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public int getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    /**
     * Gets passport id.
     *
     * @return the passport id
     */
    public String getPassportID() {
        return passportID;
    }

    @Override
    public String toString() {
        return "data.Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", height=" + height +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                '}';
    }
}