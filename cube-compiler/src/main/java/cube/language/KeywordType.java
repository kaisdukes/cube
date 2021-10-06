package cube.language;

import java.util.HashMap;
import java.util.Map;

public enum KeywordType {
    AND,
    AS,
    ELSE,
    END,
    FUNCTION,
    IF,
    INT,
    OR,
    OUTPUT,
    THEN;

    private static final Map<String, KeywordType> keywordMap = new HashMap<>();

    static {
        for (final KeywordType keywordType : values()) {
            keywordMap.put(keywordType.getText(), keywordType);
        }
    }

    public static boolean isKeyword(String text) {
        return keywordMap.containsKey(text);
    }

    public static KeywordType of(String text) {
        final KeywordType keywordType = keywordMap.get(text);
        if (keywordType == null) {
            throw new UnsupportedOperationException("Unrecognized keyword: " + text);
        }
        return keywordType;
    }

    public String getText() {
        return this.name().toLowerCase();
    }
}