package io.github.avegera.predicate4j.api;

import io.github.avegera.predicate4j.api.collection.WhereList;

import java.util.List;
import java.util.function.Function;

public interface WhereType {

    <T> WhereBoolean<T> booleanValue(Function<T, Boolean> mapper);

    <T, E> WhereList<T, List<E>, E> list(Function<T, List<E>> mapper);

    <T, N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper);

    <T> WhereString<T> string(Function<T, String> mapper);
}