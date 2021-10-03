package cube.parser;

import cube.expressions.Expression;
import cube.language.SymbolType;

import java.util.Iterator;

import static cube.expressions.ExpressionType.IDENTIFIER;
import static cube.language.Precedence.PRODUCT;
import static cube.language.SymbolType.STAR;
import static cube.tokenizer.CubeTokenizer.tokenize;

public class CubeParser extends PrattParser {

    public static Expression parse(final String text) {
        final var tokens = tokenize(text).iterator();
        final var parser = new CubeParser(tokens);
        return parser.parseExpression();
    }

    public CubeParser(final Iterator<Expression> tokens) {
        super(tokens);

        // prefix
        add(IDENTIFIER, new IdentifierParser());

        // infix
        infix(STAR, PRODUCT);
    }

    private void infix(final SymbolType tokenType, final int precedence) {
        add(tokenType, new BinaryExpressionParser(precedence));
    }
}