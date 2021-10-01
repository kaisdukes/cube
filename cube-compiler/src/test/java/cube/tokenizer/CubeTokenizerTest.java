package cube.tokenizer;

import cube.expressions.IntConstant;
import cube.expressions.Symbol;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cube.expressions.SymbolType.PLUS;
import static cube.tokenizer.CubeTokenizer.tokenize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CubeTokenizerTest {

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
    public void shouldTokenizeBinaryExpression() {
        assertThat(
                tokenize("1 + 2"),
                is(equalTo(List.of(
                        new IntConstant(1),
                        new Symbol(PLUS),
                        new IntConstant(2)))));
    }
}