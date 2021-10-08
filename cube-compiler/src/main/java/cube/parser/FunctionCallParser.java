package cube.parser;

import cube.expressions.Expression;
import cube.expressions.FunctionCallExpression;

import java.util.ArrayList;

import static cube.language.Precedence.FUNCTION_CALL;
import static cube.language.SymbolType.COMMA;
import static cube.language.SymbolType.RIGHT_PARENTHESIS;

public class FunctionCallParser implements InfixParser {

    @Override
    public int getPrecedence() {
        return FUNCTION_CALL;
    }

    @Override
    public Expression parse(final PrattParser parser, final Expression left, final Expression token) {
        final var parameters = new ArrayList<Expression>();
        if (!parser.is(RIGHT_PARENTHESIS)) {
            do {
                parameters.add(parser.parseExpression());
            } while (parser.is(COMMA));
            parser.next(RIGHT_PARENTHESIS);
        }
        return new FunctionCallExpression(left, parameters);
    }
}