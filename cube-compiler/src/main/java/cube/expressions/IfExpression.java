package cube.expressions;

import static cube.expressions.ExpressionType.IF_EXPRESSION;

public class IfExpression extends Expression {
    private final Expression condition;
    private final Expression thenExpression;
    private final Expression elseExpression;

    public IfExpression(
            final Expression condition,
            final Expression thenExpression,
            final Expression elseExpression) {
        super(IF_EXPRESSION);
        this.condition = condition;
        this.thenExpression = thenExpression;
        this.elseExpression = elseExpression;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getThenExpression() {
        return thenExpression;
    }

    public Expression getElseExpression() {
        return elseExpression;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof IfExpression)) return false;
        final IfExpression that = (IfExpression) object;
        return this.condition.equals(that.condition)
                && this.thenExpression.equals(that.thenExpression)
                && this.elseExpression.equals(that.elseExpression);
    }
}