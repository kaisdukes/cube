package cube.parser;

import cube.expressions.Expression;
import cube.language.KeywordType;
import cube.language.Precedence;
import cube.language.SymbolType;
import cube.tokenizer.CubeTokenizer;

import static cube.expressions.ExpressionType.IDENTIFIER;
import static cube.expressions.ExpressionType.INT_CONSTANT;
import static cube.language.KeywordType.AND;
import static cube.language.KeywordType.OR;
import static cube.language.SymbolType.*;

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
        add(IDENTIFIER, new TerminalParser());
        add(INT_CONSTANT, new TerminalParser());
        add(LEFT_PARENTHESIS, new ParenthesisParser());

        // infix
        infix(PLUS, Precedence.SUM);
        infix(DASH, Precedence.SUM);
        infix(STAR, Precedence.PRODUCT);
        infix(SLASH, Precedence.PRODUCT);
        infix(PERCENT, Precedence.PRODUCT);
        infix(OR, Precedence.OR);
        infix(AND, Precedence.AND);
        add(LEFT_PARENTHESIS, new FunctionCallParser());
    }

    private void infix(final SymbolType tokenType, final int precedence) {
        add(tokenType, new BinaryExpressionParser(precedence));
    }

    private void infix(final KeywordType keywordType, final int precedence) {
        add(keywordType, new BinaryExpressionParser(precedence));
    }
}