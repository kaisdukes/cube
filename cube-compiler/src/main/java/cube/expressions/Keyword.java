package cube.expressions;

import cube.language.KeywordType;
import cube.language.SymbolType;

import static cube.expressions.ExpressionType.KEYWORD;
import static cube.expressions.ExpressionType.SYMBOL;

public class Keyword extends Expression {
    private final KeywordType keywordType;

    public Keyword(KeywordType keywordType) {
        super(KEYWORD);
        this.keywordType = keywordType;
    }

    public KeywordType getKeywordType() {
        return keywordType;
    }

    @Override
    public boolean equals(final Object object) {
        return (object instanceof Keyword) && ((Keyword) object).keywordType == keywordType;
    }
}