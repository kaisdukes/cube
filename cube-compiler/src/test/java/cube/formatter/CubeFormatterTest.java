package cube.formatter;

import cube.expressions.Identifier;
import cube.expressions.IntConstant;
import cube.expressions.Symbol;
import org.junit.jupiter.api.Test;

import static cube.expressions.SymbolType.PLUS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CubeFormatterTest {

    @Test
    public void shouldFormatPlusSign() {
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
    public void shouldFormatIdentifier() {
        assertThat(
                new Identifier("xyz").toString(),
                is(equalTo("xyz")));
    }
}