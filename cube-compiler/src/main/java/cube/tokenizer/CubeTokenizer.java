package cube.tokenizer;

import cube.expressions.Expression;
import cube.expressions.ExpressionType;

import java.util.List;

public class CubeTokenizer {
    private ExpressionType tokenType;
    private int tokenStart;
    private int tokenEnd;

    public static List<Expression> tokenize(final String text) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public void setBuffer(
            CharSequence buffer,
            int startOffset,
            int endOffset) {
        throw new UnsupportedOperationException();
    }

    public void next() {
        throw new UnsupportedOperationException();
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
}