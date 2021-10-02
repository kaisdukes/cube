package cube.expressions;

import cube.language.OperatorType;

import static cube.expressions.ExpressionType.BINARY_EXPRESSION;

public class BinaryExpression extends Expression {
    private final OperatorType operatorType;
    private final Expression left;
    private final Expression right;

    public BinaryExpression(
            final OperatorType operatorType, final Expression left, final Expression right) {
        super(BINARY_EXPRESSION);
        this.operatorType = operatorType;
        this.left = left;
        this.right = right;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof BinaryExpression)) return false;
        final BinaryExpression that = (BinaryExpression) object;
        return this.operatorType == that.operatorType
                && this.left.equals(that.left)
                && this.right.equals(that.right);
    }
}