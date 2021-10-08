package cube.parser;

import cube.expressions.Expression;
import cube.expressions.Keyword;
import cube.expressions.UnaryExpression;
import cube.language.OperatorType;

import static cube.language.OperatorType.NOT;

public class UnaryExpressionParser implements PrefixParser {
    private final int precedence;

    public UnaryExpressionParser(final int precedence) {
        this.precedence = precedence;
    }

    @Override
    public Expression parse(final PrattParser parser, final Expression token) {
        final Expression right = parser.parseExpression(precedence);
        return new UnaryExpression(getOperator(token), right);
    }

    public int getPrecedence() {
        return precedence;
    }

    private OperatorType getOperator(final Expression token) {

        if (token instanceof Keyword) {
            final var keyword = (Keyword) token;
            return switch (keyword.getKeywordType()) {
                case NOT -> NOT;
                default -> throw new UnsupportedOperationException(
                        "The keyword type " + keyword.getKeywordType() + " is not supported.");
            };
        }

        throw new UnsupportedOperationException(
                "The token type " + token + " is not supported.");
    }
}