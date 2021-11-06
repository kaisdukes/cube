package cube.parser;

import cube.expressions.Expression;
import cube.expressions.IfExpression;

import static cube.language.KeywordType.ELSE;
import static cube.language.KeywordType.THEN;

public class IfExpressionParser implements PrefixParser {

    @Override
    public Expression parse(final PrattParser parser, final Expression token) {

        // condition
        final Expression condition = parser.parseExpression();

        // then
        parser.next(THEN);
        final Expression thenExpression = parser.parseExpression();

        // else
        parser.next(ELSE);
        final Expression elseExpression = parser.parseExpression();
        return new IfExpression(condition, thenExpression, elseExpression);
    }
}