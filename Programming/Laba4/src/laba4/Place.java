package laba4;

public enum Place {
    TABLE("Стол"),
    WICKER("Заросли"),
    POTATO_FIELD("Картофельное поле"),
    ASYLUM("Убежище"),
    BONFIRE("Костёр");

    private final String name;

    Place(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
