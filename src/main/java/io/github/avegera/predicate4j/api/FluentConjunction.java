package io.github.avegera.predicate4j.api;

import io.github.avegera.predicate4j.api.collection.WhereCollection;
import io.github.avegera.predicate4j.api.collection.WhereIterable;
import io.github.avegera.predicate4j.api.collection.WhereList;
import io.github.avegera.predicate4j.api.collection.WhereSet;
import io.github.avegera.predicate4j.api.quantifier.WhereAtLeast;
import io.github.avegera.predicate4j.api.quantifier.WhereEach;
import io.github.avegera.predicate4j.api.quantifier.WhereExactly;
import io.github.avegera.predicate4j.api.quantifier.WhereNone;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public interface FluentConjunction<T> {

    WhereBoolean<T> booleanValue(Function<T, Boolean> mapper);

    <E> WhereCollection<T, Collection<E>, E> collection(Function<T, Collection<E>> mapper);

    <E> WhereIterable<T, Iterable<E>, E> iterable(Function<T, Iterable<E>> mapper);

    <E> WhereList<T, List<E>, E> list(Function<T, List<E>> mapper);

    <E> WhereSet<T, Set<E>, E> set(Function<T, Set<E>> mapper);

    <N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper);

    WhereString<T> string(Function<T, String> mapper);

    <E> WhereAtLeast<T, E> atLeast(int times, Function<T, Iterable<E>> mapper);

    <E> WhereAtLeast<T, E> atLeastOne(Function<T, Iterable<E>> mapper);

    <E> WhereExactly<T, E> exactly(int times, Function<T, Iterable<E>> mapper);

    <E> WhereExactly<T, E> exactlyOne(Function<T, Iterable<E>> mapper);

    <E> WhereEach<T, E> each(Function<T, Iterable<E>> mapper);

    <E> WhereNone<T, E> none(Function<T, Iterable<E>> mapper);
}