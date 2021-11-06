package cube.expressions;

public enum ExpressionType {
    INT_CONSTANT,
    SYMBOL,
    KEYWORD,
    IDENTIFIER,
    UNARY_EXPRESSION,
    BINARY_EXPRESSION,
    EOF,
    FUNCTION_CALL,
    IF_EXPRESSION;
}