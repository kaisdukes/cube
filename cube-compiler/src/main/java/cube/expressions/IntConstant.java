package cube.expressions;

import static cube.expressions.ExpressionType.INT_CONSTANT;

public class IntConstant extends Expression {
    private final int value;

    public IntConstant(final int value) {
        super(INT_CONSTANT);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        return (object instanceof IntConstant) && ((IntConstant) object).value == value;
    }
}