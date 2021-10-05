package cube.parser;

import cube.expressions.EofToken;
import cube.expressions.Expression;
import cube.expressions.ExpressionType;
import cube.expressions.Symbol;
import cube.language.SymbolType;
import cube.tokenizer.CubeTokenizer;

import java.util.*;

public abstract class PrattParser {
    private final Map<ExpressionType, PrefixParser> tokenPrefixParsers = new HashMap<>();
    private final Map<SymbolType, InfixParser> symbolInfixParsers = new HashMap<>();
    private final CubeTokenizer tokenizer;
    private final List<Expression> read = new ArrayList<>();

    protected PrattParser(CubeTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Expression parseExpression() {
        return parseExpression(0);
    }

    public Expression parseExpression(final int precedence) {
        Expression token = next();
        PrefixParser prefix = tokenPrefixParsers.get(token.getExpressionType());
        if (prefix == null)
            throw new UnsupportedOperationException(
                    "The token type " + token.getExpressionType() + " is not supported.");

        Expression left = prefix.parse(this, token);

        while (precedence < getPrecedence()) {
            token = next();

            InfixParser infix = symbolInfixParsers.get(((Symbol) token).getSymbolType());
            left = infix.parse(this, left, token);
        }

        return left;
    }

    protected void add(final ExpressionType tokenType, final PrefixParser parser) {
        tokenPrefixParsers.put(tokenType, parser);
    }

    protected void add(final SymbolType symbolType, final InfixParser parser) {
        symbolInfixParsers.put(symbolType, parser);
    }

    private int getPrecedence() {
        final var next = lookAhead(0);
        if (!(next instanceof EofToken)) {
            InfixParser parser = symbolInfixParsers.get(((Symbol) next).getSymbolType());
            if (parser != null) return parser.getPrecedence();
        }
        return 0;
    }

    private Expression next() {
        lookAhead(0);
        return read.remove(0);
    }

    private Expression lookAhead(int n) {
        while (read.size() <= n) {
            tokenizer.next();
            read.add(tokenizer.getToken());
        }
        return read.get(n);
    }
}