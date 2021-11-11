package cube.formatter;

import cube.expressions.*;
import cube.language.OperatorType;

import java.util.List;

import static cube.language.OperatorType.NEGATE;

public class CubeFormatter {
    private final StringBuilder text = new StringBuilder();
    private int indent;

    public void format(final Expression expression) {
        switch (expression.getExpressionType()) {
            case SYMBOL -> text.append(((Symbol) expression).getSymbolType().getText());
            case INT_CONSTANT -> text.append(((IntConstant) expression).getValue());
            case KEYWORD -> text.append(((Keyword) expression).getKeywordType().getText());
            case IDENTIFIER -> text.append(((Identifier) expression).getText());
            case UNARY_EXPRESSION -> formatUnaryExpression((UnaryExpression) expression);
            case BINARY_EXPRESSION -> formatBinaryExpression((BinaryExpression) expression);
            case FUNCTION -> formatFunction((FunctionExpression) expression);
            case FUNCTION_CALL -> formatFunctionCall((FunctionCallExpression) expression);
            case IF_EXPRESSION -> formatIfExpression((IfExpression) expression);
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

    private void formatFunction(final FunctionExpression expression) {

        // name
        text.append("function ");
        format(expression.getName());

        // type
        text.append(" as ");
        format(expression.getType());

        // parameters
        text.append('(');
        final List<Expression> parameters = expression.getParameters();
        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) text.append(", ");
            format(parameters.get(i));
        }
        text.append(')');

        // block
        final List<Expression> block = expression.getBlock();
        indent++;
        for (final Expression value : block) {
            newLine();
            format(value);
        }
        indent--;
        newLine();
        text.append("end");
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

    private void formatIfExpression(final IfExpression expression) {
        text.append("if ");
        format(expression.getCondition());
        text.append(" then ");
        format(expression.getThenExpression());
        text.append(" else ");
        format(expression.getElseExpression());
    }

    private void formatWithBrackets(final Expression expression) {
        final var brackets = expression instanceof BinaryExpression;
        if (brackets) text.append('(');
        format(expression);
        if (brackets) text.append(')');
    }

    private void newLine() {
        text.append('\n');
        text.append("    ".repeat(indent));
    }
}