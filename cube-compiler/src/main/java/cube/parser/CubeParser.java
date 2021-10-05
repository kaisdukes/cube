package cube.parser;

import cube.expressions.Expression;
import cube.language.SymbolType;
import cube.tokenizer.CubeTokenizer;

import static cube.expressions.ExpressionType.IDENTIFIER;
import static cube.language.Precedence.PRODUCT;
import static cube.language.SymbolType.STAR;

public class CubeParser extends PrattParser {

    public static Expression parse(final String text) {

        // Tokenizer.
        final var tokenizer = new CubeTokenizer();
        tokenizer.setText(text, 0, text.length());

        // Parse.
        final var parser = new CubeParser(tokenizer);
        return parser.parseExpression();
    }

    public CubeParser(final CubeTokenizer tokenizer) {
        super(tokenizer);

        // prefix
        add(IDENTIFIER, new IdentifierParser());

        // infix
        infix(STAR, PRODUCT);
    }

    private void infix(final SymbolType tokenType, final int precedence) {
        add(tokenType, new BinaryExpressionParser(precedence));
    }
}