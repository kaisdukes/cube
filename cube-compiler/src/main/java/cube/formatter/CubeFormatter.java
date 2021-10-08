package cube.formatter;

import cube.expressions.*;
import cube.language.OperatorType;

import java.util.List;

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
            case FUNCTION_CALL -> formatFunctionCall((FunctionCallExpression) expression);
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
        formatWithBrackets(expression.getLeft());
        text.append(' ');
        text.append(expression.getOperatorType().getText());
        text.append(' ');
        formatWithBrackets(expression.getRight());
    }

    private void formatFunctionCall(final FunctionCallExpression expression) {
        format(expression.getTarget());
        text.append('(');
        final List<Expression> parameters = expression.getParameters();
        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) text.append(", ");
            format(parameters.get(i));
        }
        text.append(')');
    }

    private void formatWithBrackets(final Expression expression) {
        final var brackets = expression instanceof BinaryExpression;
        if (brackets) text.append('(');
        format(expression);
        if (brackets) text.append(')');
    }
}