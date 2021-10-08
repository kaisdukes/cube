package cube.expressions;

import java.util.List;

import static cube.expressions.ExpressionType.FUNCTION_CALL;

public class FunctionCallExpression extends Expression {
    private final Expression target;
    private final List<Expression> parameters;

    public FunctionCallExpression(final Expression target, final List<Expression> parameters) {
        super(FUNCTION_CALL);
        this.target = target;
        this.parameters = parameters;
    }

    public Expression getTarget() {
        return target;
    }

    public List<Expression> getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof FunctionCallExpression)) return false;
        final FunctionCallExpression that = (FunctionCallExpression) object;
        if (!target.equals(that.target)) return false;
        if (parameters.size() != that.parameters.size()) return false;
        for (int i = 0; i < parameters.size(); i++) {
            if (!parameters.get(i).equals(that.parameters.get(i))) return false;
        }
        return true;
    }
}