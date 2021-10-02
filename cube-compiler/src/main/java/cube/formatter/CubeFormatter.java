package cube.formatter;

import cube.expressions.*;
import cube.language.OperatorType;

import static cube.language.OperatorType.NEGATE;

public class CubeFormatter {
    private final StringBuilder text = new StringBuilder();

    public void format(final Expression expression) {
        switch (expression.getExpressionType()) {
            case SYMBOL -> text.append(((Symbol) expression).getSymbolType().getText());
            case INT_CONSTANT -> text.append(((IntConstant) expression).getValue());
            case KEYWORD -> text.append(((Keyword) expression).getKeywordType().getText());
            case IDENTIFIER -> text.append(((Identifier) expression).getText());
            case UNARY_EXPRESSION -> formatUnaryExpression((UnaryExpression) expression);
            case BINARY_EXPRESSION -> formatBinaryExpression((BinaryExpression) expression);
            default -> throw new UnsupportedOperationException(
                    "The expression type " + expression.getExpressionType() + " is not supported.");
        }
    }

    public String toString() {
        return text.toString();
    }

    private void formatUnaryExpression(final UnaryExpression expression) {
        final OperatorType operatorType = expression.getOperatorType();
        text.append(operatorType.getText());
        if (operatorType != NEGATE) {
            text.append(' ');
        }
        format(expression.getExpression());
    }

    private void formatBinaryExpression(final BinaryExpression expression) {
        format(expression.getLeft());
        text.append(' ');
        text.append(expression.getOperatorType().getText());
        text.append(' ');
        format(expression.getRight());
    }
}