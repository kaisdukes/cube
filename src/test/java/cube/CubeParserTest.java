package cube;

import org.junit.jupiter.api.Test;

import static cube.CubeParser.parse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CubeParserTest {

    @Test
    public void shouldThrowNotImplementedException() {
        final var exception = assertThrows(
                UnsupportedOperationException.class,
                () -> parse("1 + 1"));

        assertThat(exception.getMessage(), is(equalTo("Not implemented.")));
    }
}