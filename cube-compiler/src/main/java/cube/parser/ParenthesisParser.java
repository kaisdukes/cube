package cube.parser;

import cube.expressions.Expression;

import static cube.language.SymbolType.RIGHT_PARENTHESIS;

public class ParenthesisParser implements PrefixParser {

    @Override
    public Expression parse(final PrattParser parser, final Expression token) {
        final Expression expression = parser.parseExpression();
        parser.next(RIGHT_PARENTHESIS);
        return expression;
    }
}