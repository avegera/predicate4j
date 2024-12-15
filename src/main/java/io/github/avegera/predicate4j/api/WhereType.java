package io.github.avegera.predicate4j.api;

import io.github.avegera.predicate4j.api.collection.WhereCollection;
import io.github.avegera.predicate4j.api.collection.WhereIterable;
import io.github.avegera.predicate4j.api.collection.WhereList;
import io.github.avegera.predicate4j.api.collection.WhereSet;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public interface WhereType {

    <T> WhereBoolean<T> booleanValue(Function<T, Boolean> mapper);

    <T, E> WhereCollection<T, Collection<E>, E> collection(Function<T, Collection<E>> mapper);

    <T, E> WhereIterable<T, Iterable<E>, E> iterable(Function<T, Iterable<E>> mapper);

    <T, E> WhereList<T, List<E>, E> list(Function<T, List<E>> mapper);

    <T, E> WhereSet<T, Set<E>, E> set(Function<T, Set<E>> mapper);

    <T, N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper);

    <T> WhereString<T> string(Function<T, String> mapper);
}