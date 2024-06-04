package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.api.RichPredicate;

import java.util.function.Function;
import java.util.function.Predicate;

public class RichPredicateImpl<T, R> implements RichPredicate<T> {

    private final Function<T, R> mapper;

    private final Predicate<R> predicate;

    public RichPredicateImpl(Function<T, R> mapper, Predicate<R> predicate) {
        this.mapper = mapper;
        this.predicate = predicate;
    }

    @Override
    public boolean test(T object) {
        return predicate.test(mapper.apply(object));
    }
}