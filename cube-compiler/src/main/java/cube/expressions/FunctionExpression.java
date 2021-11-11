package cube.expressions;

import java.util.List;

import static cube.expressions.ExpressionType.FUNCTION;

public class FunctionExpression extends Expression {
    private final Identifier name;
    private final Expression type;
    private final List<Expression> parameters;
    private final List<Expression> block;

    public FunctionExpression(
            final Identifier name,
            Expression type,
            List<Expression> parameters,
            List<Expression> block) {
        super(FUNCTION);
        this.name = name;
        this.type = type;
        this.parameters = parameters;
        this.block = block;
    }

    public Identifier getName() {
        return name;
    }

    public Expression getType() {
        return type;
    }

    public List<Expression> getParameters() {
        return parameters;
    }

    public List<Expression> getBlock() {
        return block;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof FunctionExpression)) return false;
        final FunctionExpression that = (FunctionExpression) object;

        // name
        if (!name.equals(that.name)) return false;

        // type
        if (!type.equals(that.type)) return false;

        // parameters
        if (parameters.size() != that.parameters.size()) return false;
        for (int i = 0; i < parameters.size(); i++) {
            if (!parameters.get(i).equals(that.parameters.get(i))) return false;
        }

        // block
        if (block.size() != that.block.size()) return false;
        for (int i = 0; i < block.size(); i++) {
            if (!block.get(i).equals(that.block.get(i))) return false;
        }
        return true;
    }
}