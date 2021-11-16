package cube.parser;

import cube.expressions.Expression;
import cube.expressions.FunctionExpression;
import cube.expressions.Identifier;

import java.util.ArrayList;

import static cube.language.KeywordType.AS;
import static cube.language.KeywordType.END;
import static cube.language.SymbolType.*;

public class FunctionParser implements PrefixParser {

    @Override
    public Expression parse(final PrattParser parser, final Expression token) {

        // name
        final Identifier name = parser.nextIdentifier();

        // type
        parser.next(AS);
        final Identifier type = parser.nextIdentifier();

        // parameters
        final var parameters = new ArrayList<Expression>();
        parser.next(LEFT_PARENTHESIS);
        if (!parser.nextIs(RIGHT_PARENTHESIS)) {
            do {
                parameters.add(parser.parseExpression());
            } while (parser.nextIs(COMMA));
            parser.next(RIGHT_PARENTHESIS);
        }

        // block
        final var block = new ArrayList<Expression>();
        while (!parser.nextIs(END)) {
            block.add(parser.parseExpression());
        }
        return new FunctionExpression(name, type, parameters, block);
    }
}