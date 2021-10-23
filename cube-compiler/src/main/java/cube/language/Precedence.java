package cube.language;

public class Precedence {
    // http://www.cs.bilkent.edu.tr/~guvenir/courses/CS101/op_precedence.html
    public static final int ASSIGNMENT = 1;
    public static final int TERNARY_CONDITIONAL = 2;
    public static final int LOGICAL_OR = 3;
    public static final int LOGICAL_AND = 4;
    public static final int BITWISE_INCLUSIVE_OR = 5;
    public static final int BITWISE_EXCLUSIVE_OR = 6;
    public static final int BITWISE_AND = 7;
    public static final int RELATIONAL_EQUAL = 8;
    public static final int RELATIONAL_LESS_THAN = 9;
    public static final int BITWISE_LEFT_SHIFT = 10;
    public static final int ADDITION = 11;
    public static final int MULTIPLICATION = 12;
    public static final int UNARY_PRE_INCREMENT = 13;
    public static final int UNARY_POST_INCREMENT = 14;
    public static final int FUNCTION_CALL = 15;
}