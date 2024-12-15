package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.WhereObject;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

import static io.github.avegera.predicate4j.Predicates.alwaysTrue;

public class WhereObjectImpl<T, R> implements WhereObject<T, R> {

    protected final Function<T, R> mapper;

    protected final FluentPredicate<T> previousPredicate;

    public WhereObjectImpl(Function<T, R> mapper) {
        this.mapper = mapper;
        this.previousPredicate = null;
    }

    public WhereObjectImpl(Function<T, R> mapper, FluentPredicate<T> previousPredicate) {
        this.mapper = mapper;
        this.previousPredicate = previousPredicate;
    }

    @Override
    public FluentPredicate<T> isEqualTo(R value) {
        return getPredicate(Predicates.isEqualTo(value));
    }

    @Override
    public FluentPredicate<T> isInstanceOf(Class<?> clazz) {
        return getPredicate(Predicates.isInstanceOf(clazz));
    }

    @Override
    public FluentPredicate<T> isNull() {
        return getPredicate(Predicates.isNull());
    }

    @Override
    public FluentPredicate<T> notEqualTo(R value) {
        return getPredicate(Predicates.notEqualTo(value));
    }

    @Override
    public FluentPredicate<T> notInstanceOf(Class<?> clazz) {
        return getPredicate(Predicates.notInstanceOf(clazz));
    }

    @Override
    public FluentPredicate<T> notNull() {
        return getPredicate(Predicates.notNull());
    }

    @Override
    public FluentPredicate<T> in(Collection<R> collection) {
        return getPredicate(Predicates.in(collection));
    }

    @Override
    public FluentPredicate<T> notIn(Collection<R> collection) {
        return getPredicate(Predicates.notIn(collection));
    }

    @Override
    public FluentPredicate<T> accepts(Predicate<R> predicate) {
        return getPredicate(predicate);
    }

    @Override
    public FluentPredicate<T> rejects(Predicate<R> predicate) {
        return getPredicate(predicate == null ? alwaysTrue() : predicate.negate());
    }

    protected FluentPredicate<T> getPredicate(Predicate<R> predicate) {
        Predicate<T> currentPredicate = getPredicateWithMapper(mapper, predicate);
        if (previousPredicate == null) {
            return new FluentPredicateImpl<>(currentPredicate);
        }
        return new FluentPredicateImpl<>(previousPredicate.and(currentPredicate));
    }

    protected Integer getInt(T object, Function<R, Integer> intFunction) {
        R result = mapper.apply(object);
        return result != null ? intFunction.apply(result) : 0;
    }

    private Predicate<T> getPredicateWithMapper(Function<T, R> mapper, Predicate<R> predicate) {
        return object -> object != null && predicate != null && mapper != null && predicate.test(mapper.apply(object));
    }
}