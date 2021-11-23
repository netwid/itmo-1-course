public enum Place {
    TABLE("Стол"),
    WICKER("Заросли"),
    POTATO_FIELD("Картофельное поле"),
    ASYLUM("Убежище");

    private final String name;

    Place(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
