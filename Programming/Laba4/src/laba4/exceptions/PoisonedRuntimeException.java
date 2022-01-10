package laba4.exceptions;

public class PoisonedRuntimeException extends RuntimeException {
    private String name;

    public String getName() {
        return this.name;
    }

    public PoisonedRuntimeException(String name) {
        this.name = name;
    }
}
