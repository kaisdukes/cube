package cube.parser;

import cube.expressions.Expression;

public class TerminalParser implements PrefixParser {

    @Override
    public Expression parse(final PrattParser parser, final Expression token) {
        return token;
    }
}