package cube.expressions;

import static cube.expressions.ExpressionType.SYMBOL;

public class Symbol extends Expression {
    private final SymbolType symbolType;

    public Symbol(SymbolType symbolType) {
        super(SYMBOL);
        this.symbolType = symbolType;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    @Override
    public boolean equals(final Object object) {
        return (object instanceof Symbol) && ((Symbol) object).symbolType == symbolType;
    }
}