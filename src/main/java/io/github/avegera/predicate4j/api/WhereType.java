package io.github.avegera.predicate4j.api;

import java.util.List;
import java.util.function.Function;

public interface WhereType {

    <T> WhereBoolean<T> booleanValue(Function<T, Boolean> mapper);

    <T, R> WhereList<T, R> list(Function<T, List<R>> mapper);

    <T, N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper);

    <T> WhereString<T> string(Function<T, String> mapper);
}