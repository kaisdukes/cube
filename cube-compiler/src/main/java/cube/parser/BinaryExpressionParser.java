package cube.parser;

import cube.expressions.BinaryExpression;
import cube.expressions.Expression;
import cube.expressions.Keyword;
import cube.expressions.Symbol;
import cube.language.OperatorType;

import static cube.language.OperatorType.*;

public class BinaryExpressionParser implements InfixParser {
    private final int precedence;

    public BinaryExpressionParser(final int precedence) {
        this.precedence = precedence;
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }

    @Override
    public Expression parse(final PrattParser parser, final Expression left, final Expression token) {
        Expression right = parser.parseExpression(precedence);
        return new BinaryExpression(getOperator(token), left, right);
    }

    private OperatorType getOperator(final Expression token) {

        if (token instanceof Keyword) {
            final var keyword = (Keyword) token;
            return switch (keyword.getKeywordType()) {
                case AND -> AND;
                case OR -> OR;
                default -> throw new UnsupportedOperationException(
                        "The keyword type " + keyword.getKeywordType() + " is not supported.");
            };
        }

        if (!(token instanceof Symbol))
            throw new UnsupportedOperationException(
                    "Expected SYMBOL not " + token.getExpressionType());

        final var symbol = (Symbol) token;
        return switch (symbol.getSymbolType()) {
            case PLUS -> ADD;
            case DASH -> SUBTRACT;
            case STAR -> MULTIPLY;
            case SLASH -> DIVIDE;
            default -> throw new UnsupportedOperationException(
                    "The symbol type " + symbol.getSymbolType() + " is not supported.");
        };
    }
}