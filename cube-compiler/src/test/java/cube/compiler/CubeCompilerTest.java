package cube.compiler;

import cube.runtime.RuntimeClassLoader;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CubeCompilerTest {

    @Test
    public void shouldCompileFactorialFunction() {

        // compile
        final CubeCompiler compiler = new CubeCompiler();
        final byte[] byteCode = compiler.compile();

        // load
        final var className = "cube.examples.Factorial";
        final var classLoader = new RuntimeClassLoader();
        final var type = classLoader.define(className, byteCode);
        assertThat(type.getName(), is(equalTo(className)));
    }
}