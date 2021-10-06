package cube.parser;

import cube.expressions.*;
import cube.language.KeywordType;
import cube.language.SymbolType;
import cube.tokenizer.CubeTokenizer;

import java.util.*;

import static cube.expressions.ExpressionType.SYMBOL;

public abstract class PrattParser {
    private final Map<ExpressionType, PrefixParser> tokenPrefixParsers = new HashMap<>();
    private final Map<SymbolType, PrefixParser> symbolPrefixParsers = new HashMap<>();
    private final Map<SymbolType, InfixParser> symbolInfixParsers = new HashMap<>();
    private final Map<KeywordType, InfixParser> keywordInfixParsers = new HashMap<>();
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
        PrefixParser prefix = token instanceof Symbol
                ? symbolPrefixParsers.get(((Symbol) token).getSymbolType())
                : tokenPrefixParsers.get(token.getExpressionType());
        if (prefix == null)
            throw new UnsupportedOperationException(
                    "The token " + token.getExpressionType() + ' ' + token + " is not supported.");

        Expression left = prefix.parse(this, token);

        while (precedence < getPrecedence()) {
            token = next();
            InfixParser infix = token instanceof Keyword
                    ? keywordInfixParsers.get(((Keyword) token).getKeywordType())
                    : symbolInfixParsers.get(((Symbol) token).getSymbolType());
            left = infix.parse(this, left, token);
        }

        return left;
    }

    protected void add(final ExpressionType tokenType, final PrefixParser parser) {
        tokenPrefixParsers.put(tokenType, parser);
    }

    protected void add(final SymbolType symbolType, final PrefixParser parser) {
        symbolPrefixParsers.put(symbolType, parser);
    }

    protected void add(final SymbolType symbolType, final InfixParser parser) {
        symbolInfixParsers.put(symbolType, parser);
    }

    protected void add(final KeywordType keywordType, final InfixParser parser) {
        keywordInfixParsers.put(keywordType, parser);
    }

    public Expression next(final SymbolType symbolType) {
        Expression token = lookAhead(0);
        if (token.getExpressionType() != SYMBOL || ((Symbol) token).getSymbolType() != symbolType) {
            throw new RuntimeException(
                    "Expected " + symbolType + " not " + token.getExpressionType() + ' ' + token);
        }
        return next();
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

    private int getPrecedence() {
        final var next = lookAhead(0);
        if (next instanceof EofToken) return 0;
        InfixParser parser = next instanceof Keyword
                ? keywordInfixParsers.get(((Keyword) next).getKeywordType())
                : symbolInfixParsers.get(((Symbol) next).getSymbolType());
        if (parser != null) return parser.getPrecedence();
        return 0;
    }

}