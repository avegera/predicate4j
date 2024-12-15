package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.api.FluentConjunction;
import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.WhereObject;

import java.util.function.Function;
import java.util.function.Predicate;

public class FluentPredicateImpl<T> implements FluentPredicate<T> {

    private final Predicate<T> predicate;

    public FluentPredicateImpl(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(T object) {
        return predicate.test(object);
    }

    @Override
    public <R2> WhereObject<T, R2> and(Function<T, R2> andMapper) {
        return new WhereObjectImpl<>(andMapper, this);
    }

    @Override
    public FluentConjunction<T> and() {
        return new FluentConjunctionImpl<>(this);
    }
}