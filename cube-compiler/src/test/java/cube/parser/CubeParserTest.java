package cube.parser;

import cube.expressions.BinaryExpression;
import cube.expressions.Identifier;
import cube.expressions.IntConstant;
import org.junit.jupiter.api.Test;

import static cube.language.OperatorType.*;
import static cube.parser.CubeParser.parse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CubeParserTest {

    @Test
    public void shouldParseSumOfIntConstants() {
        assertThat(
                parse("1 + 2"),
                is(equalTo(new BinaryExpression(ADD,
                        new IntConstant(1),
                        new IntConstant(2)))));
    }

    @Test
    public void shouldParseProductOfIdentifiers() {
        assertThat(
                parse("a * b"),
                is(equalTo(new BinaryExpression(MULTIPLY,
                        new Identifier("a"),
                        new Identifier("b")))));
    }

    @Test
    public void shouldParseParenthesis() {
        assertThat(
                parse("a * (b + c)"),
                is(equalTo(new BinaryExpression(MULTIPLY,
                        new Identifier("a"),
                        new BinaryExpression(ADD,
                                new Identifier("b"),
                                new Identifier("c"))))));
    }

    @Test
    public void shouldParseSubtraction() {
        assertThat(
                parse("x - y"),
                is(equalTo(new BinaryExpression(SUBTRACT,
                        new Identifier("x"),
                        new Identifier("y")))));
    }

    @Test
    public void shouldParseDivision() {
        assertThat(
                parse("a / 2"),
                is(equalTo(new BinaryExpression(DIVIDE,
                        new Identifier("a"),
                        new IntConstant(2)))));
    }

    @Test
    public void shouldParseAndOrLogicalExpression() {
        assertThat(
                parse("a and b or c"),
                is(equalTo(new BinaryExpression(OR,
                        new BinaryExpression(AND,
                                new Identifier("a"),
                                new Identifier("b")),
                        new Identifier("c")))));
    }
}