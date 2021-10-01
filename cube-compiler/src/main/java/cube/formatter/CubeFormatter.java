package cube.formatter;

import cube.expressions.*;

public class CubeFormatter {
    private final StringBuilder text = new StringBuilder();

    public void format(final Expression expression) {
        switch (expression.getExpressionType()) {
            case SYMBOL -> text.append(((Symbol) expression).getSymbolType().getText());
            case INT_CONSTANT -> text.append(((IntConstant) expression).getValue());
            case KEYWORD -> text.append(((Keyword) expression).getKeywordType().getText());
            case IDENTIFIER -> text.append(((Identifier) expression).getText());
            default -> throw new UnsupportedOperationException(
                    "The expression type " + expression.getExpressionType() + " is not supported.");
        }
    }

    public String toString() {
        return text.toString();
    }
}