# The Cube Programming Language

A JVM language, focused on improving both code reability and automated testing compared to Java.

## Example Code

The factorial function:

```
function factorial as int (n as int)
    output if n == 0 then 1 else n * factorial(n - 1)
end
```