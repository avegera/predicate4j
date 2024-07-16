package io.github.avegera.predicate4j.api;

import java.util.List;
import java.util.function.Function;

public interface RichPredicateConjunction<T> {

    WhereBoolean<T> booleanValue(Function<T, Boolean> mapper);

    <R> WhereList<T, R> list(Function<T, List<R>> mapper);

    <N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper);

    WhereString<T> string(Function<T, String> mapper);
}