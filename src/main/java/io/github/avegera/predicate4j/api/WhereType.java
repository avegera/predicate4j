package io.github.avegera.predicate4j.api;

import java.util.function.Function;

public interface WhereType {

    <T> WhereBoolean<T> booleanValue(Function<T, Boolean> mapper);
}