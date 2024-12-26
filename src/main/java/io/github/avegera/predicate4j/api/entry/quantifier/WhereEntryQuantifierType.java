package io.github.avegera.predicate4j.api.entry.quantifier;

import io.github.avegera.predicate4j.api.type.common.WhereBoolean;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;
import io.github.avegera.predicate4j.api.type.common.WhereString;

import java.util.function.Function;

public interface WhereEntryQuantifierType {

    <T> WhereBoolean<T> booleanValue(Function<T, Iterable<Boolean>> mapper);

    <T, N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, Iterable<N>> mapper);

    <T> WhereString<T> string(Function<T, Iterable<String>> mapper);
}