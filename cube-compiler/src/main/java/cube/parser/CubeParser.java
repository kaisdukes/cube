package cube.parser;

import cube.expressions.Expression;
import cube.language.KeywordType;
import cube.language.Precedence;
import cube.language.SymbolType;
import cube.tokenizer.CubeTokenizer;

import static cube.expressions.ExpressionType.IDENTIFIER;
import static cube.expressions.ExpressionType.INT_CONSTANT;
import static cube.language.KeywordType.*;
import static cube.language.SymbolType.*;

public class CubeParser extends PrattParser {

    public static Expression parse(final String text) {

        // Tokenizer.
        final var tokenizer = new CubeTokenizer();
        tokenizer.setText(text, 0, text.length());

        // Parse.
        final var parser = new CubeParser(tokenizer);
        return parser.  parseExpression();
    }

    public CubeParser(final CubeTokenizer tokenizer) {
        super(tokenizer);

        // prefix
        add(IDENTIFIER, new TerminalParser());
        add(INT_CONSTANT, new TerminalParser());
        add(LEFT_PARENTHESIS, new ParenthesisParser());
        add(IF, new IfExpressionParser());
        add(FUNCTION, new FunctionParser());
        prefix(NOT, Precedence.UNARY_PRE_INCREMENT);

        // infix
        infix(PLUS, Precedence.ADDITION);
        infix(DASH, Precedence.ADDITION);
        infix(STAR, Precedence.MULTIPLICATION);
        infix(SLASH, Precedence.MULTIPLICATION);
        infix(PERCENT, Precedence.MULTIPLICATION);
        infix(OR, Precedence.LOGICAL_OR);
        infix(AND, Precedence.LOGICAL_AND);
        infix(EQUALS, Precedence.ASSIGNMENT);
        infix(EQUALITY, Precedence.RELATIONAL_EQUAL);
        infix(LESS_THAN, Precedence.RELATIONAL_LESS_THAN);
        infix(LESS_THAN_OR_EQUAL, Precedence.RELATIONAL_LESS_THAN);
        infix(GREATER_THAN, Precedence.RELATIONAL_LESS_THAN);
        infix(GREATER_THAN_OR_EQUAL, Precedence.RELATIONAL_LESS_THAN);
        infix(AS, Precedence.TYPE_EXPRESSION);
        add(LEFT_PARENTHESIS, new FunctionCallParser());
    }

    private void prefix(KeywordType keywordType, int precedence) {
        add(keywordType, new UnaryExpressionParser(precedence));
    }

    private void infix(final SymbolType tokenType, final int precedence) {
        add(tokenType, new BinaryExpressionParser(precedence));
    }

    private void infix(final KeywordType keywordType, final int precedence) {
        add(keywordType, new BinaryExpressionParser(precedence));
    }
}