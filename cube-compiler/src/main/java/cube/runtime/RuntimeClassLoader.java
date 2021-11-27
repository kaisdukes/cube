package cube.runtime;

public class RuntimeClassLoader extends ClassLoader {

    public Class<?> define(final String name, final byte[] byteCode) {
        return this.defineClass(name, byteCode, 0, byteCode.length);
    }
}