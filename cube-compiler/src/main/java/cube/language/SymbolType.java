package cube.language;

import java.util.HashMap;
import java.util.Map;

public enum SymbolType {
    PLUS("+"),
    STAR("*"),
    LEFT_PARENTHESIS("("),
    RIGHT_PARENTHESIS(")");

    private final String text;
    private static final Map<String, SymbolType> symbolMap = new HashMap<>();

    static {
        for (final SymbolType symbolType : values()) {
            symbolMap.put(symbolType.getText(), symbolType);
        }
    }

    SymbolType(final String text) {
        this.text = text;
    }

    public static SymbolType of(String text) {
        final SymbolType symbolType = symbolMap.get(text);
        if (symbolType == null) {
            throw new UnsupportedOperationException("Unrecognized symbol: " + text);
        }
        return symbolType;
    }

    public String getText() {
        return text;
    }
}