package cube.tokenizer;

import cube.expressions.*;
import cube.language.KeywordType;
import cube.language.SymbolType;

import java.util.ArrayList;
import java.util.List;

import static cube.expressions.ExpressionType.*;
import static java.lang.Integer.parseInt;

public class CubeTokenizer {
    private ExpressionType tokenType;
    private int tokenStart;
    private int tokenEnd;
    private CharSequence text;
    private int position;
    private int end;

    public static List<Expression> tokenize(final String text) {

        // initiate
        final var tokens = new ArrayList<Expression>();
        final var tokenizer = new CubeTokenizer();
        tokenizer.setText(text, 0, text.length());

        // tokenize
        while (true) {
            tokenizer.next();
            ExpressionType tokenType = tokenizer.getTokenType();
            if (tokenType == EOF) break;
            tokens.add(tokenizer.getToken());
        }
        return tokens;
    }

    public void setText(CharSequence text, int startOffset, int endOffset) {
        this.text = text;
        this.position = startOffset;
        this.end = endOffset;
    }

    public void next() {

        // whitespace
        while (canRead() && whitespace(peek())) {
            position++;
        }

        // done?
        if (!canRead()) {
            tokenType = EOF;
            return;
        }

        final char ch = peek();
        if (digit(ch)) readNumber();
        else if (identifierStart(ch)) readIdentifierOrKeyword();
        else readSymbol(ch);
    }

    public Expression getToken() {
        if (tokenType == EOF) return new EofToken();
        return switch (tokenType) {
            case SYMBOL -> new Symbol(SymbolType.of(getTokenText()));
            case INT_CONSTANT -> new IntConstant(parseInt(getTokenText()));
            case KEYWORD -> new Keyword(KeywordType.of(getTokenText()));
            case IDENTIFIER -> new Identifier(getTokenText());
            default -> throw new UnsupportedOperationException("Unsupported token type: " + tokenType);
        };
    }

    public ExpressionType getTokenType() {
        return tokenType;
    }

    public int getTokenStart() {
        return tokenStart;
    }

    public int getTokenEnd() {
        return tokenEnd;
    }

    public String getTokenText() {
        return text.subSequence(getTokenStart(), getTokenEnd()).toString();
    }

    private void readNumber() {
        tokenType = INT_CONSTANT;
        tokenStart = position++;
        while (canRead() && digit(peek())) {
            position++;
        }
        tokenEnd = position;
    }

    private void readIdentifierOrKeyword() {
        tokenStart = position++;
        while (canRead() && identifierPart(peek())) {
            position++;
        }
        tokenEnd = position;
        tokenType = KeywordType.isKeyword(getTokenText()) ? KEYWORD : IDENTIFIER;
    }

    private void readSymbol(final char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%'
                || ch == '(' || ch == ')' || ch == ',' || ch == '=') {
            tokenType = SYMBOL;
            tokenStart = position;
            tokenEnd = ++position;
            return;
        }
        throw new UnsupportedOperationException("The character '" + ch + "' is not recognized.");
    }

    private boolean canRead() {
        return position < end;
    }

    private char peek() {
        return text.charAt(position);
    }

    private boolean whitespace(final char ch) {
        return ch == ' ';
    }

    private boolean digit(final char ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean identifierStart(final char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_';
    }

    private boolean identifierPart(final char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_' || (ch >= '0' && ch <= '9');
    }
}