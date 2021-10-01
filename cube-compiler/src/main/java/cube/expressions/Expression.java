package cube.expressions;

public abstract class Expression {
    private final ExpressionType expressionType;

    public Expression(final ExpressionType expressionType) {
        this.expressionType = expressionType;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }
}