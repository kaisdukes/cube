package cube.language;

public enum OperatorType {
    ADD("+"),
    AND("and"),
    DIVIDE("/"),
    MODULO("%"),
    MULTIPLY("*"),
    NEGATE("-"),
    NOT("not"),
    OR("or"),
    SUBTRACT("-");

    private final String text;

    OperatorType(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}