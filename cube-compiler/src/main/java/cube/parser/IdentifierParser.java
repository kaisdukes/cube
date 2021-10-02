package cube.parser;

import cube.expressions.Expression;
import cube.expressions.Identifier;

public class IdentifierParser implements PrefixParser {

    @Override
    public Expression parse(final PrattParser parser, final Expression token) {
        if (!(token instanceof Identifier))
            throw new UnsupportedOperationException(
                    "Expected IDENTIFIER not " + token.getExpressionType());

        return token;
    }
}