package io.github.avegera.predicate4j.api.core;

import io.github.avegera.predicate4j.api.conjunction.common.WhereConjunction;
import io.github.avegera.predicate4j.api.type.common.WhereObject;

import java.util.function.Function;
import java.util.function.Predicate;

public interface FluentPredicate<T> extends Predicate<T> {

    <R> WhereObject<T, R> and(Function<T, R> mapper);

    WhereConjunction<T> and();
}