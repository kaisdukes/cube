package cube.tokenizer;

import cube.expressions.IntConstant;
import cube.expressions.Symbol;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cube.expressions.SymbolType.*;
import static cube.tokenizer.CubeTokenizer.tokenize;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CubeTokenizerTest {

    @Test
    public void shouldTokenizeWhitespace() {
        assertThat(
                tokenize("    "),
                is(equalTo(emptyList())));
    }

    @Test
    public void shouldTokenizePlusSign() {
        assertThat(
                tokenize("+"),
                is(equalTo(List.of(
                        new Symbol(PLUS)))));
    }

    @Test
    public void shouldTokenizeZero() {
        assertThat(
                tokenize("0"),
                is(equalTo(List.of(
                        new IntConstant(0)))));
    }

    @Test
    public void shouldTokenizeZerosWithWhitespace() {
        assertThat(
                tokenize("    0    0     0     "),
                is(equalTo(List.of(
                        new IntConstant(0),
                        new IntConstant(0),
                        new IntConstant(0)))));
    }

    @Test
    public void shouldTokenizePositiveIntConstants() {
        assertThat(
                tokenize("1 1000 16580192 2147483647"),
                is(equalTo(List.of(
                        new IntConstant(1),
                        new IntConstant(1000),
                        new IntConstant(16580192),
                        new IntConstant(Integer.MAX_VALUE)))));
    }

    @Test
    public void shouldTokenizeBinaryExpression() {
        assertThat(
                tokenize("1 + 2"),
                is(equalTo(List.of(
                        new IntConstant(1),
                        new Symbol(PLUS),
                        new IntConstant(2)))));
    }

    @Test
    public void shouldTokenizeBinaryExpressionWithoutWhitespace() {
        assertThat(
                tokenize("3+4"),
                is(equalTo(List.of(
                        new IntConstant(3),
                        new Symbol(PLUS),
                        new IntConstant(4)))));
    }

    @Test
    public void shouldTokenizeBinaryExpressionWithMultiplication() {
        assertThat(
                tokenize("42 * 56"),
                is(equalTo(List.of(
                        new IntConstant(42),
                        new Symbol(STAR),
                        new IntConstant(56)))));
    }

    @Test
    public void shouldTokenizeParenthesis() {
        assertThat(
                tokenize("2 * (5 + 6)"),
                is(equalTo(List.of(
                        new IntConstant(2),
                        new Symbol(STAR),
                        new Symbol(LEFT_PARENTHESIS),
                        new IntConstant(5),
                        new Symbol(PLUS),
                        new IntConstant(6),
                        new Symbol(RIGHT_PARENTHESIS)))));
    }
}