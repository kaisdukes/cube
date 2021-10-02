package cube.expressions;

import cube.language.OperatorType;

import static cube.expressions.ExpressionType.UNARY_EXPRESSION;

public class UnaryExpression extends Expression {
    private final OperatorType operatorType;
    private final Expression expression;

    public UnaryExpression(final OperatorType operatorType, final Expression expression) {
        super(UNARY_EXPRESSION);
        this.operatorType = operatorType;
        this.expression = expression;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof UnaryExpression)) return false;
        final UnaryExpression that = (UnaryExpression) object;
        return this.operatorType == that.operatorType && this.expression.equals(that.expression);
    }
}