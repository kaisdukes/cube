package cube.tokenizer;

import cube.expressions.IntConstant;
import cube.expressions.Symbol;
import org.junit.jupiter.api.Test;

import static cube.expressions.SymbolType.PLUS;
import static cube.tokenizer.CubeTokenizer.tokenize;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CubeTokenizerTest {

    @Test
    public void shouldTokenizeBinaryExpression() {
        assertThat(
                tokenize("1 + 2"),
                is(equalTo(asList(
                        new IntConstant(1),
                        new Symbol(PLUS),
                        new IntConstant(2)))));
    }
}