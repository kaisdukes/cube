package cube.tokenizer;

import cube.expressions.*;

import java.util.ArrayList;
import java.util.List;

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
            if (tokenType == null) break;
            switch (tokenType) {
                case SYMBOL -> tokens.add(new Symbol(SymbolType.of(tokenizer.getTokenText())));
                case INT_CONSTANT -> tokens.add(new IntConstant(parseInt(tokenizer.getTokenText())));
                default -> throw new UnsupportedOperationException("Unsupported token type: " + tokenType);
            }
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
            tokenType = null;
            return;
        }

        final char ch = peek();
        if (ch >= '0' && ch <= '9') readNumber();
        else readSymbol(ch);
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
        // TODO: Extend this to support more than a single digit.
        tokenType = ExpressionType.INT_CONSTANT;
        tokenStart = position;
        tokenEnd = ++position;
    }

    private void readSymbol(final char ch) {
        if (ch == '+') {
            tokenType = ExpressionType.SYMBOL;
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
}