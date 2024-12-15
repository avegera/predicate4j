package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.api.RichPredicate;
import io.github.avegera.predicate4j.api.RichPredicateConjunction;
import io.github.avegera.predicate4j.api.WhereObject;

import java.util.function.Function;
import java.util.function.Predicate;

public class RichPredicateImpl<T> implements RichPredicate<T> {

    private final Predicate<T> predicate;

    public RichPredicateImpl(Predicate<T> predicate) {
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
    public RichPredicateConjunction<T> and() {
        return new RichPredicateConjunctionImpl<>(this);
    }
}