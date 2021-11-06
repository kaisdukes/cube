package cube.language;

public class Precedence {
    // http://www.cs.bilkent.edu.tr/~guvenir/courses/CS101/op_precedence.html
    public static final int ASSIGNMENT = 1;
    public static final int LOGICAL_OR = 2;
    public static final int LOGICAL_AND = 3;
    public static final int RELATIONAL_EQUAL = 4;
    public static final int RELATIONAL_LESS_THAN = 5;
    public static final int ADDITION = 6;
    public static final int MULTIPLICATION = 7;
    public static final int UNARY_PRE_INCREMENT = 8;
    public static final int FUNCTION_CALL = 9;
}