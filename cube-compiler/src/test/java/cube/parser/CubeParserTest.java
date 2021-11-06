package cube.parser;

import cube.expressions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cube.language.OperatorType.*;
import static cube.parser.CubeParser.parse;
import static java.util.Collections.emptyList;
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
    public void shouldParseModulo() {
        assertThat(
                parse("a % 4"),
                is(equalTo(new BinaryExpression(MODULO,
                        new Identifier("a"),
                        new IntConstant(4)))));
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

    @Test
    public void shouldParseNotLogicalExpression() {
        assertThat(
                parse("not x"),
                is(equalTo(new UnaryExpression(NOT, new Identifier("x")))));
    }

    @Test
    public void shouldParseFunctionCallWithoutParameters() {
        assertThat(
                parse("foo()"),
                is(equalTo(new FunctionCallExpression(
                        new Identifier("foo"),
                        emptyList()))));
    }

    @Test
    public void shouldParseFunctionCallWithSingleParameter() {
        assertThat(
                parse("foo(a)"),
                is(equalTo(new FunctionCallExpression(
                        new Identifier("foo"),
                        List.of(new Identifier("a"))))));
    }

    @Test
    public void shouldParseFunctionCallWithTwoParameters() {
        assertThat(
                parse("bar(x, y)"),
                is(equalTo(new FunctionCallExpression(
                        new Identifier("bar"),
                        List.of(new Identifier("x"), new Identifier("y"))))));
    }

    @Test
    public void shouldParseBinaryExpressionWithFunctionCall() {
        assertThat(
                parse("n * factorial(n - 1)"),
                is(equalTo(new BinaryExpression(MULTIPLY,
                        new Identifier("n"),
                        new FunctionCallExpression(
                                new Identifier("factorial"),
                                List.of(new BinaryExpression(SUBTRACT,
                                        new Identifier("n"),
                                        new IntConstant(1))))))));
    }

    @Test
    public void shouldParseBinaryExpressionOfFunctionCalls() {
        assertThat(
                parse("foo() + bar()"),
                is(equalTo(
                        new BinaryExpression(ADD,
                                new FunctionCallExpression(new Identifier("foo"), emptyList()),
                                new FunctionCallExpression(new Identifier("bar"), emptyList())))));
    }

    @Test
    public void shouldParseAssignment() {
        assertThat(
                parse("a = 2"),
                is(equalTo(new BinaryExpression(ASSIGN,
                        new Identifier("a"),
                        new IntConstant(2)))));
    }

    @Test
    public void shouldParseEquality() {
        assertThat(
                parse("i == 0"),
                is(equalTo(new BinaryExpression(EQUALITY,
                        new Identifier("i"),
                        new IntConstant(0)))));
    }

    @Test
    public void shouldParseLess() {
        assertThat(
                parse("i < 0"),
                is(equalTo(new BinaryExpression(LESS,
                        new Identifier("i"),
                        new IntConstant(0)))));
    }

    @Test
    public void shouldParseAssignmentWithEquality() {
        assertThat(
                parse("a = i == 0"),
                is(equalTo(new BinaryExpression(ASSIGN,
                        new Identifier("a"),
                        new BinaryExpression(EQUALITY,
                                new Identifier("i"),
                                new IntConstant(0))))));
    }

    @Test
    public void shouldParseIfThenElse() {
        assertThat(
                parse("if i == 0 then 1 else 2"),
                is(equalTo(
                        new IfExpression(
                                new BinaryExpression(EQUALITY,
                                        new Identifier("i"),
                                        new IntConstant(0)),
                                new IntConstant(1),
                                new IntConstant(2)))));
    }

    @Test
    public void shouldParseTypeExpression() {
        assertThat(
                parse("foo as int"),
                is(equalTo(
                        new BinaryExpression(AS,
                                new Identifier("foo"),
                                new Identifier("int")))));
    }
}