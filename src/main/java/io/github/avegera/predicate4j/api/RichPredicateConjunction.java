package io.github.avegera.predicate4j.api;

import io.github.avegera.predicate4j.api.collection.WhereList;

import java.util.List;
import java.util.function.Function;

public interface RichPredicateConjunction<T> {

    WhereBoolean<T> booleanValue(Function<T, Boolean> mapper);

    <E> WhereList<T, List<E>, E> list(Function<T, List<E>> mapper);

    <N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper);

    WhereString<T> string(Function<T, String> mapper);
}