package cube.formatter;

import cube.expressions.Expression;
import cube.expressions.IntConstant;
import cube.expressions.Symbol;

public class CubeFormatter {
    private final StringBuilder text = new StringBuilder();

    public void format(final Expression expression) {
        switch (expression.getExpressionType()) {
            case SYMBOL -> text.append(((Symbol) expression).getSymbolType().getText());
            case INT_CONSTANT -> text.append(((IntConstant) expression).getValue());
            default -> throw new UnsupportedOperationException(
                    "The expression type " + expression.getExpressionType() + " is not supported.");
        }
    }

    public String toString() {
        return text.toString();
    }
}