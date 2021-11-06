package cube.language;

public enum OperatorType {
    ADD("+"),
    AND("and"),
    AS("as"),
    ASSIGN("="),
    DIVIDE("/"),
    EQUALITY("=="),
    LESS("<"),
    LESS_OR_EQUAL("<="),
    GREATER(">"),
    GREATER_OR_EQUAL(">="),
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