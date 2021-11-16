package cube.parser;

import cube.expressions.*;
import cube.language.KeywordType;
import cube.language.SymbolType;
import cube.tokenizer.CubeTokenizer;

import java.util.*;

import static cube.expressions.ExpressionType.KEYWORD;
import static cube.expressions.ExpressionType.SYMBOL;

public abstract class PrattParser {
    private final Map<ExpressionType, PrefixParser> tokenPrefixParsers = new HashMap<>();
    private final Map<SymbolType, PrefixParser> symbolPrefixParsers = new HashMap<>();
    private final Map<KeywordType, PrefixParser> keywordPrefixParsers = new HashMap<>();
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

        // token
        Expression token = next();

        // prefix
        PrefixParser prefix;
        if (token instanceof Symbol) prefix = symbolPrefixParsers.get(((Symbol) token).getSymbolType());
        else if (token instanceof Keyword) prefix = keywordPrefixParsers.get(((Keyword) token).getKeywordType());
        else prefix = tokenPrefixParsers.get(token.getExpressionType());
        if (prefix == null)
            throw new UnsupportedOperationException(
                    "The " + token.getExpressionType() + ' ' + token + " is not supported.");
        Expression left = prefix.parse(this, token);

        // infix
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

    protected void add(final KeywordType keywordType, final PrefixParser parser) {
        keywordPrefixParsers.put(keywordType, parser);
    }

    protected void add(final SymbolType symbolType, final InfixParser parser) {
        symbolInfixParsers.put(symbolType, parser);
    }

    protected void add(final KeywordType keywordType, final InfixParser parser) {
        keywordInfixParsers.put(keywordType, parser);
    }

    public boolean nextIs(final SymbolType symbolType) {
        final var token = lookAhead(0);
        if (token.getExpressionType() != SYMBOL || ((Symbol) token).getSymbolType() != symbolType) {
            return false;
        }
        next();
        return true;
    }

    public boolean nextIs(final KeywordType keywordType) {
        final var token = lookAhead(0);
        if (token.getExpressionType() != KEYWORD || ((Keyword) token).getKeywordType() != keywordType) {
            return false;
        }
        next();
        return true;
    }

    public Expression next(final SymbolType symbolType) {
        final var token = lookAhead(0);
        if (token.getExpressionType() != SYMBOL || ((Symbol) token).getSymbolType() != symbolType) {
            throw new UnsupportedOperationException(
                    "Expected " + symbolType + " not " + token.getExpressionType() + ' ' + token);
        }
        return next();
    }

    public Expression next(final KeywordType keywordType) {
        final var token = lookAhead(0);
        if (token.getExpressionType() != KEYWORD || ((Keyword) token).getKeywordType() != keywordType) {
            throw new UnsupportedOperationException(
                    "Expected " + keywordType + " not " + token.getExpressionType() + ' ' + token);
        }
        return next();
    }

    public Identifier nextIdentifier() {
        final var token = lookAhead(0);
        if (!(token instanceof Identifier)) {
            throw new UnsupportedOperationException(
                    "Expected identifier not " + token.getExpressionType() + ' ' + token);
        }
        return (Identifier) next();
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
        InfixParser parser;
        if (next instanceof Keyword) {
            parser = keywordInfixParsers.get(((Keyword) next).getKeywordType());
        } else if (next instanceof Symbol) {
            parser = symbolInfixParsers.get(((Symbol) next).getSymbolType());
        } else {
            return 0;
        }
        if (parser != null) return parser.getPrecedence();
        return 0;
    }
}