package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.RichPredicate;
import io.github.avegera.predicate4j.api.WhereObject;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereObjectImpl<T, R> implements WhereObject<T, R> {

    private final Function<T, R> mapper;

    private final RichPredicate<T> previousPredicate;

    public WhereObjectImpl(Function<T, R> mapper) {
        this.mapper = mapper;
        this.previousPredicate = null;
    }

    public WhereObjectImpl(Function<T, R> mapper, RichPredicate<T> previousPredicate) {
        this.mapper = mapper;
        this.previousPredicate = previousPredicate;
    }

    @Override
    public RichPredicate<T> isEqualTo(R value) {
        return getPredicate(Predicates.isEqualTo(value));
    }

    @Override
    public RichPredicate<T> isInstanceOf(Class<?> clazz) {
        return getPredicate(Predicates.isInstanceOf(clazz));
    }

    @Override
    public RichPredicate<T> isNull() {
        return getPredicate(Predicates.isNull());
    }

    @Override
    public RichPredicate<T> notEqualTo(R value) {
        return getPredicate(Predicates.notEqualTo(value));
    }

    @Override
    public RichPredicate<T> notInstanceOf(Class<?> clazz) {
        return getPredicate(Predicates.notInstanceOf(clazz));
    }

    @Override
    public RichPredicate<T> notNull() {
        return getPredicate(Predicates.notNull());
    }

    protected RichPredicate<T> getPredicate(Predicate<R> predicate) {
        Predicate<T> currentPredicate = getPredicateWithMapper(mapper, predicate);
        if (previousPredicate == null) {
            return new RichPredicateImpl<>(currentPredicate);
        }
        return new RichPredicateImpl<>(previousPredicate.and(currentPredicate));
    }

    private Predicate<T> getPredicateWithMapper(Function<T, R> mapper, Predicate<R> predicate) {
        return object -> object != null && predicate.test(mapper.apply(object));
    }
}