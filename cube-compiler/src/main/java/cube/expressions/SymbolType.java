package cube.expressions;

public enum SymbolType {
    PLUS;

    public static SymbolType of(String text) {
        return switch (text) {
            case "+" -> PLUS;
            default -> throw new UnsupportedOperationException("Unrecognized symbol: " + text);
        };
    }
}