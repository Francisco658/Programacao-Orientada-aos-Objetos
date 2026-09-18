package exceptions;

public class InputIncorreto extends Exception {
    public InputIncorreto() {
        super("Tipo do Input Incorreto");
    }

    public InputIncorreto(String message) {
        super(message);
    }
}