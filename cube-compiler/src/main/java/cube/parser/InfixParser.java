package cube.parser;

import cube.expressions.Expression;

public interface InfixParser {
    int getPrecedence();

    Expression parse(PrattParser parser, Expression left, Expression token);
}