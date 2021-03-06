package cube.tokenizer;

import cube.expressions.Identifier;
import cube.expressions.IntConstant;
import cube.expressions.Keyword;
import cube.expressions.Symbol;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cube.language.KeywordType.*;
import static cube.language.SymbolType.*;
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
    public void shouldTokenizeEqualsSign() {
        assertThat(
                tokenize("="),
                is(equalTo(List.of(
                        new Symbol(EQUALS)))));
    }

    @Test
    public void shouldTokenizeRelationalSymbols() {
        assertThat(
                tokenize("< <= > >="),
                is(equalTo(List.of(
                        new Symbol(LESS_THAN),
                        new Symbol(LESS_THAN_OR_EQUAL),
                        new Symbol(GREATER_THAN),
                        new Symbol(GREATER_THAN_OR_EQUAL)))));
    }

    @Test
    public void shouldTokenizeEqualitySign() {
        assertThat(
                tokenize("=="),
                is(equalTo(List.of(
                        new Symbol(EQUALITY)))));
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

    @Test
    public void shouldTokenizeIdentifiers() {
        assertThat(
                tokenize("a + b + xyz"),
                is(equalTo(List.of(
                        new Identifier("a"),
                        new Symbol(PLUS),
                        new Identifier("b"),
                        new Symbol(PLUS),
                        new Identifier("xyz")))));
    }

    @Test
    public void shouldTokenizeFunctionKeywords() {
        assertThat(
                tokenize("function foo as int"),
                is(equalTo(List.of(
                        new Keyword(FUNCTION),
                        new Identifier("foo"),
                        new Keyword(AS),
                        new Identifier("int")))));
    }

    @Test
    public void shouldTokenizeLogicalKeywords() {
        assertThat(
                tokenize("a and b or not c"),
                is(equalTo(List.of(
                        new Identifier("a"),
                        new Keyword(AND),
                        new Identifier("b"),
                        new Keyword(OR),
                        new Keyword(NOT),
                        new Identifier("c")))));
    }

    @Test
    public void shouldTokenizeDashSign() {
        assertThat(
                tokenize("-"),
                is(equalTo(List.of(
                        new Symbol(DASH)))));
    }

    @Test
    public void shouldTokenizeSlash() {
        assertThat(
                tokenize("/"),
                is(equalTo(List.of(
                        new Symbol(SLASH)))));
    }

    @Test
    public void shouldTokenizeModuloSign() {
        assertThat(
                tokenize("%"),
                is(equalTo(List.of(
                        new Symbol(PERCENT)))));
    }

    @Test
    public void shouldTokenizeComma() {
        assertThat(
                tokenize(","),
                is(equalTo(List.of(
                        new Symbol(COMMA)))));
    }
}