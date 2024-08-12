package io.github.avegera.predicate4j.test.util;

import java.io.Serializable;
import java.util.function.Function;

public interface Lambda<T, R> extends Function<T, R>, Serializable {

}