package io.github.avegera.predicate4j.api;

import java.util.List;
import java.util.function.Function;

public interface RichPredicateConjunction<T> {

    WhereBoolean<T> booleanValue(Function<T, Boolean> mapper);

    <R> WhereList<T, R> list(Function<T, List<R>> mapper);

    WhereString<T> string(Function<T, String> mapper);
}