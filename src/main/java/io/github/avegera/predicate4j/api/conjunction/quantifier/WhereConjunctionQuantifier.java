package io.github.avegera.predicate4j.api.conjunction.quantifier;

import io.github.avegera.predicate4j.api.type.common.WhereObject;

import java.util.function.Function;

public interface WhereConjunctionQuantifier<T> {

    WhereConjunctionQuantifierType<T> atLeast(int times);

    <E> WhereObject<T, E> atLeast(int times, Function<T, Iterable<E>> mapper);

    WhereConjunctionQuantifierType<T> atLeastOne();

    <E> WhereObject<T, E> atLeastOne(Function<T, Iterable<E>> mapper);

    WhereConjunctionQuantifierType<T> exactly(int times);

    <E> WhereObject<T, E> exactly(int times, Function<T, Iterable<E>> mapper);

    WhereConjunctionQuantifierType<T> exactlyOne();

    <E> WhereObject<T, E> exactlyOne(Function<T, Iterable<E>> mapper);

    WhereConjunctionQuantifierType<T> each();

    <E> WhereObject<T, E> each(Function<T, Iterable<E>> mapper);

    WhereConjunctionQuantifierType<T> none();

    <E> WhereObject<T, E> none(Function<T, Iterable<E>> mapper);
}