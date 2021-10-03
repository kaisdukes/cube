package cube.parser;

import cube.expressions.BinaryExpression;
import cube.expressions.Identifier;
import org.junit.jupiter.api.Test;

import static cube.language.OperatorType.MULTIPLY;
import static cube.parser.CubeParser.parse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CubeParserTest {

    @Test
    public void shouldThrowNotImplementedException() {
        assertThat(
                parse("a * b"),
                is(equalTo(new BinaryExpression(MULTIPLY,
                        new Identifier("a"),
                        new Identifier("b")))));
    }
}