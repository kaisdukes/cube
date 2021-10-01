package cube.expressions;

import cube.formatter.CubeFormatter;

public abstract class Expression {
    private final ExpressionType expressionType;

    public Expression(final ExpressionType expressionType) {
        this.expressionType = expressionType;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    @Override
    public String toString() {
        final CubeFormatter formatter = new CubeFormatter();
        formatter.format(this);
        return formatter.toString();
    }
}