package cube.formatter;

import cube.expressions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cube.language.KeywordType.FUNCTION;
import static cube.language.OperatorType.*;
import static cube.language.SymbolType.PLUS;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CubeFormatterTest {

    @Test
    public void shouldFormatSymbol() {
        assertThat(
                new Symbol(PLUS).toString(),
                is(equalTo("+")));
    }

    @Test
    public void shouldFormatIntConstant() {
        assertThat(
                new IntConstant(16580192).toString(),
                is(equalTo("16580192")));
    }

    @Test
    public void shouldFormatKeyword() {
        assertThat(
                new Keyword(FUNCTION).toString(),
                is(equalTo("function")));
    }

    @Test
    public void shouldFormatIdentifier() {
        assertThat(
                new Identifier("xyz").toString(),
                is(equalTo("xyz")));
    }

    @Test
    public void shouldFormatNegation() {
        assertThat(
                new UnaryExpression(NEGATE, new Identifier("x")).toString(),
                is(equalTo("-x")));
    }

    @Test
    public void shouldFormatNotOperator() {
        assertThat(
                new UnaryExpression(NOT, new Identifier("x")).toString(),
                is(equalTo("not x")));
    }

    @Test
    public void shouldFormatBinaryExpression() {
        assertThat(
                new BinaryExpression(ADD, new Identifier("x"), new Identifier("y")).toString(),
                is(equalTo("x + y")));
    }

    @Test
    public void shouldFormatAssignment() {
        assertThat(
                new BinaryExpression(ASSIGN, new Identifier("x"), new Identifier("y")).toString(),
                is(equalTo("x = y")));
    }

    @Test
    public void shouldFormatEquality() {
        assertThat(
                new BinaryExpression(EQUALITY, new Identifier("x"), new Identifier("y")).toString(),
                is(equalTo("x == y")));
    }

    @Test
    public void shouldFormatNestedBinaryExpression() {
        assertThat(
                new BinaryExpression(
                        MULTIPLY,
                        new UnaryExpression(NEGATE, new Identifier("a")),
                        new BinaryExpression(ADD, new Identifier("x"), new Identifier("y"))).toString(),
                is(equalTo("-a * (x + y)")));
    }

    @Test
    public void shouldFormatFunctionCallWithoutParameters() {
        assertThat(
                new FunctionCallExpression(
                        new Identifier("foo"),
                        emptyList()).toString(),
                is(equalTo("foo()")));
    }

    @Test
    public void shouldFormatFunctionCallWithSingleParameter() {
        assertThat(
                new FunctionCallExpression(
                        new Identifier("foo"),
                        List.of(new Identifier("a"))).toString(),
                is(equalTo("foo(a)")));
    }

    @Test
    public void shouldFormatFunctionCallWithTwoParameters() {
        assertThat(
                new FunctionCallExpression(
                        new Identifier("bar"),
                        List.of(new Identifier("x"), new Identifier("y")))
                        .toString(),
                is(equalTo("bar(x, y)")));
    }

    @Test
    public void shouldFormatIfThenElse() {
        assertThat(
                new IfExpression(
                        new BinaryExpression(EQUALITY,
                                new Identifier("i"),
                                new IntConstant(0)),
                        new IntConstant(1),
                        new IntConstant(2))
                        .toString(),
                is(equalTo("if i == 0 then 1 else 2")));
    }

    @Test
    public void shouldFormatVoidFunction() {
        assertThat(
                new FunctionExpression(
                        new Identifier("foo"),
                        new Identifier("void"),
                        emptyList(),
                        List.of(
                                new FunctionCallExpression(
                                        new Identifier("a"),
                                        emptyList()),
                                new FunctionCallExpression(
                                        new Identifier("b"),
                                        emptyList()),
                                new IntConstant(5)))
                        .toString(),
                is(equalTo("""
                        function foo as void ()
                            a()
                            b()
                            5
                        end""")));
    }

    @Test
    public void shouldFormatFunctionWithParameters() {
        assertThat(
                new FunctionExpression(
                        new Identifier("add"),
                        new Identifier("int"),
                        List.of(
                                new BinaryExpression(AS,
                                        new Identifier("a"),
                                        new Identifier("int")),
                                new BinaryExpression(AS,
                                        new Identifier("b"),
                                        new Identifier("int"))),
                        List.of(
                                new BinaryExpression(ADD,
                                        new Identifier("a"),
                                        new Identifier("b"))))
                        .toString(),
                is(equalTo("""
                        function add as int (a as int, b as int)
                            a + b
                        end""")));
    }
}