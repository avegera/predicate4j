package io.github.avegera.predicate4j.test.util;

import java.util.Map;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class MapUtils {

    @SafeVarargs
    public static <T> Map<String, Function<T, Object>> getMap(Lambda<T, Object>... getters) {
        return stream(getters).collect(toMap(LambdaUtils::getMethodName, identity()));
    }
}