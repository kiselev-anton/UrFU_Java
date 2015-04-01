package calculator;

@SuppressWarnings("serial")
public class ParseValueException extends Exception {
    public ParseValueException() {
        super("Wrong string format.");
    }
    public ParseValueException(String details) {
        super("Wrong string format. " + details);
    }
}
