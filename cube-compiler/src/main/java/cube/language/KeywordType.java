package cube.language;

import java.util.Map;
import java.util.TreeMap;

public enum KeywordType {
    AS,
    ELSE,
    END,
    FUNCTION,
    IF,
    INT,
    OUTPUT,
    THEN;

    private static final Map<String, KeywordType> keywordMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        for (final KeywordType keywordType : values()) {
            keywordMap.put(keywordType.getText(), keywordType);
        }
    }

    public static boolean isKeyword(String text) {
        return keywordMap.containsKey(text);
    }

    public String getText() {
        return this.name().toLowerCase();
    }
}