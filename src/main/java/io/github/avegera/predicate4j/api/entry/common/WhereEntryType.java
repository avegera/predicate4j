package io.github.avegera.predicate4j.api.entry.common;

import io.github.avegera.predicate4j.api.type.collection.WhereCollection;
import io.github.avegera.predicate4j.api.type.collection.WhereIterable;
import io.github.avegera.predicate4j.api.type.collection.WhereList;
import io.github.avegera.predicate4j.api.type.collection.WhereSet;
import io.github.avegera.predicate4j.api.type.common.WhereBoolean;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;
import io.github.avegera.predicate4j.api.type.common.WhereString;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public interface WhereEntryType {

    <T> WhereBoolean<T> booleanValue(Function<T, Boolean> mapper);

    <T, E> WhereCollection<T, Collection<E>, E> collection(Function<T, Collection<E>> mapper);

    <T, E> WhereIterable<T, Iterable<E>, E> iterable(Function<T, Iterable<E>> mapper);

    <T, E> WhereList<T, List<E>, E> list(Function<T, List<E>> mapper);

    <T, E> WhereSet<T, Set<E>, E> set(Function<T, Set<E>> mapper);

    <T, N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper);

    <T> WhereString<T> string(Function<T, String> mapper);
}