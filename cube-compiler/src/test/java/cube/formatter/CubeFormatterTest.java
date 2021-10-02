package cube.formatter;

import cube.expressions.*;
import org.junit.jupiter.api.Test;

import static cube.language.KeywordType.FUNCTION;
import static cube.language.OperatorType.*;
import static cube.language.SymbolType.PLUS;
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
    public void shouldFormatEquality() {
        assertThat(
                new BinaryExpression(EQUALITY, new Identifier("x"), new Identifier("y")).toString(),
                is(equalTo("x == y")));
    }
}