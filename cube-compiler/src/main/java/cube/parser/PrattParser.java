package cube.parser;

import cube.expressions.Expression;

public abstract class PrattParser {

    public static void parse(final String text) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public Expression parseExpression(final int precedence) {
        throw new UnsupportedOperationException("Not implemented.");
    }
}