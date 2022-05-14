package data;

import client.Client;

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

    private Person(String name, LocalDateTime birthday, int height, double weight, String passportID) {
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
     * Input person.
     *
     * @return the person
     */
    public static Person input() {
        String name = inputName();
        LocalDateTime birthday = inputBirthday();
        int height = inputHeight();
        double weight = inputWeight();
        String passportId = inputPassportId();

        return new Person(name, birthday, height, weight, passportId);
    }

    /**
     * Input name.
     *
     * @return the name
     */
    private static String inputName() {
        System.out.print("Введите имя: ");
        String name = IO.get();
        if (name.isEmpty()) {
            System.out.println("Имя не может быть пустым");
            return inputName();
        }
        return name;
    }

    /**
     * Input birthday.
     *
     * @return the birthday
     */
    private static LocalDateTime inputBirthday() {
        try {
            System.out.print("Введите дату рождения (yyyy-mm-dd): ");
            return LocalDate.parse(IO.get(), DateTimeFormatter.ISO_DATE).atStartOfDay();
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
    private static int inputHeight() {
        try {
            System.out.print("Введите рост: ");
            int height = Integer.parseInt(IO.get());
            if (height <= 0)
                throw new Exception("Рост должен быть больше нуля");
            return height;
        }
        catch (NumberFormatException e) {
            System.out.println("Рост должен быть числом");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return inputHeight();
    }

    /**
     * Input weight.
     *
     * @return the weight
     */
    private static double inputWeight() {
        try {
            System.out.print("Введите вес: ");
            double weight = Double.parseDouble(IO.get());
            if (weight <= 0)
                throw new Exception("Вес должен быть больше нуля");
            return weight;
        }
        catch (NumberFormatException e) {
            System.out.println("Вес должен быть числом");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return inputWeight();
    }

    /**
     * Input passport id.
     *
     * @return the passport id
     */
    private static String inputPassportId() {
        System.out.print("Введите номер паспорта: ");
        String passportId = IO.get();

        if (passportId.length() > 41) {
            System.out.println("Значение паспорта не должно превышать 41 символа");
            return inputPassportId();
        }
        if (Client.checkContainsPassport(passportId)) {
            System.out.println("Значение паспорта должно быть уникальным");
            return inputPassportId();
        }
        return passportId;
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