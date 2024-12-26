package io.github.avegera.predicate4j.api.conjunction.quantifier;

import io.github.avegera.predicate4j.api.type.common.WhereBoolean;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;
import io.github.avegera.predicate4j.api.type.common.WhereString;

import java.util.function.Function;

public interface WhereQuantifierConjunction<T> {

    WhereBoolean<T> booleanValue(Function<T, Iterable<Boolean>> mapper);

    <N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, Iterable<N>> mapper);

    WhereString<T> string(Function<T, Iterable<String>> mapper);
}