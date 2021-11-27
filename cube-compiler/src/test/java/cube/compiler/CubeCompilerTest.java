package cube.compiler;

import cube.runtime.RuntimeClassLoader;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CubeCompilerTest {

    @Test
    public void shouldCompileFactorialFunction() throws Exception {

        // compile
        final CubeCompiler compiler = new CubeCompiler();
        final byte[] byteCode = compiler.compile();

        // load
        final var className = "cube.examples.Factorial";
        final var classLoader = new RuntimeClassLoader();
        final var type = classLoader.define(className, byteCode);
        assertThat(type.getName(), is(equalTo(className)));

        // invoke default constructor
        final var defaultConstructor = type.getConstructor();
        final var object = defaultConstructor.newInstance();
        assertThat(object.getClass(), is(equalTo(type)));

        // call factorial function
        final var factorial = type.getMethod("factorial", int.class);
        assertThat(factorial.invoke(null, 0), is(equalTo(1)));
        assertThat(factorial.invoke(null, 1), is(equalTo(1)));
        assertThat(factorial.invoke(null, 5), is(equalTo(120)));
    }
}