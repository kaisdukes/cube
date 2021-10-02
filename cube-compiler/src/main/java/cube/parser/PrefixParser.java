package cube.parser;

import cube.expressions.Expression;

public interface PrefixParser {
    Expression parse(PrattParser parser, Expression token);
}