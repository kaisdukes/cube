package cube.expressions;

import static cube.expressions.ExpressionType.EOF;

public class EofToken extends Expression {

    public EofToken() {
        super(EOF);
    }

    @Override
    public boolean equals(final Object that) {
        return that instanceof EofToken;
    }
}