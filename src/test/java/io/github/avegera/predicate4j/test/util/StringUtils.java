package io.github.avegera.predicate4j.test.util;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class StringUtils {

    public static String getString(Object object, Function<Object, String> stringFunction) {
        if (object == null) {
            return "null";
        }

        if (stringFunction != null) {
            return stringFunction.apply(object);
        }

        if (object instanceof Class<?>) {
            return ((Class<?>) object).getSimpleName() + ".class";
        }

        if (object instanceof String) {
            return "\"" + object + "\"";
        }

        if (object instanceof List) {
            List<?> list = (List<?>) object;
            if (!list.isEmpty() && list.get(0) instanceof String) {
                return format("List.of(\"%s\")", String.join("\", \"", (List<String>) list));
            }
        }

        if (object instanceof Object[]) {
            Object[] array = (Object[]) object;
            return format("new %s[] {%s}", array.getClass().getComponentType().getSimpleName(), arrayToString(array));
        }

        return object.toString();
    }

    public static <T> String toStringOnlyInitialized(T object, Map<String, Function<T, Object>> initializationMap) {
        return object.getClass().getSimpleName() + "(" + initializationMap.keySet()
                .stream()
                .map(key -> getStringByKey(key, object, initializationMap))
                .collect(joining(",\n")) + ")";
    }

    private static <T> String getStringByKey(String key, T object, Map<String, Function<T, Object>> initializationMap) {
        return key + "=" + getStringValue(initializationMap.get(key).apply(object));
    }

    private static String getStringValue(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof String) {
            return "\"" + object + "\"";
        }

        if (object instanceof List) {
            List<?> list = (List<?>) object;
            if (!list.isEmpty() && list.get(0) instanceof String) {
                return format("[\"%s\"]", String.join("\", \"", (List<String>) object));
            }
        }
        //TODO: print L for Long value (pass class object serializer)
        //TODO: if array of number for cases .isFalseFor(userWithBalances(asList(null, 100, null, 200L)))
        //TODO: User(roles=["Admin", "Guest", "null"])  - should be null not "null"
        return object.toString();
    }

    private static String arrayToString(Object[] array) {
        return stream(array)
                .map(StringUtils::getStringValue)
                .collect(joining(", "));
    }
}