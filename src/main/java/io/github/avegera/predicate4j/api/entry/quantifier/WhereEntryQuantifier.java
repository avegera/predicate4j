package io.github.avegera.predicate4j.api.entry.quantifier;

import io.github.avegera.predicate4j.api.type.common.WhereObject;

import java.util.function.Function;

public interface WhereEntryQuantifier {

    WhereEntryQuantifierType atLeast(int times);

    <T, E> WhereObject<T, E> atLeast(int times, Function<T, Iterable<E>> mapper);

    WhereEntryQuantifierType atLeastOne();

    <T, E> WhereObject<T, E> atLeastOne(Function<T, Iterable<E>> mapper);

    WhereEntryQuantifierType exactly(int times);

    <T, E> WhereObject<T, E> exactly(int times, Function<T, Iterable<E>> mapper);

    WhereEntryQuantifierType exactlyOne();

    <T, E> WhereObject<T, E> exactlyOne(Function<T, Iterable<E>> mapper);

    WhereEntryQuantifierType each();

    <T, E> WhereObject<T, E> each(Function<T, Iterable<E>> mapper);

    WhereEntryQuantifierType none();

    <T, E> WhereObject<T, E> none(Function<T, Iterable<E>> mapper);
}