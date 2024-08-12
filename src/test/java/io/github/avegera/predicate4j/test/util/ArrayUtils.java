package io.github.avegera.predicate4j.test.util;

public class ArrayUtils {

    public static Object[] getArray(Object context, Object[] arguments) {
        if (arguments == null) {
            return new Object[]{context};
        }
        Object[] array = new Object[arguments.length + 1];
        array[0] = context;
        System.arraycopy(arguments, 0, array, 1, arguments.length);
        return array;
    }
}