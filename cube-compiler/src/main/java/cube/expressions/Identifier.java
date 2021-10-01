package cube.expressions;

import static cube.expressions.ExpressionType.IDENTIFIER;

public class Identifier extends Expression {
    private final String text;

    public Identifier(final String text) {
        super(IDENTIFIER);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(final Object object) {
        return (object instanceof Identifier) && ((Identifier) object).text.equals(text);
    }
}