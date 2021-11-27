package cube.compiler;

import org.junit.jupiter.api.Test;

public class CubeCompilerTest {

    @Test
    public void shouldCompileFactorialFunction() {
        final CubeCompiler compiler = new CubeCompiler();
        final byte[] byteCode = compiler.compile();
    }
}