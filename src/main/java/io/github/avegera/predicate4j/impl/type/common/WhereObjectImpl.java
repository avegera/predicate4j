package io.github.avegera.predicate4j.impl.type.common;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.common.WhereObject;
import io.github.avegera.predicate4j.impl.core.FluentPredicateImpl;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

import static io.github.avegera.predicate4j.Predicates.alwaysTrue;

public class WhereObjectImpl<T, M, R> implements WhereObject<T, R> {

    protected final Function<T, M> mapper;

    protected final FluentPredicate<T> previousPredicate;

    public WhereObjectImpl(Function<T, M> mapper) {
        this.mapper = mapper;
        this.previousPredicate = null;
    }

    public WhereObjectImpl(Function<T, M> mapper, FluentPredicate<T> previousPredicate) {
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
        Predicate<T> current = getPredicateWithMapper(mapper, predicate);
        Predicate<T> next = previousPredicate == null ? current : previousPredicate.and(current);
        return new FluentPredicateImpl<>(next);
    }

    private Predicate<T> getPredicateWithMapper(Function<T, M> mapper, Predicate<R> predicate) {
        return object -> object != null && predicate != null && mapper != null && test(mapper, predicate, object);
    }

    @SuppressWarnings("unchecked")
    protected boolean test(Function<T, M> mapper, Predicate<R> predicate, T object) {
        return predicate.test((R) mapper.apply(object));
    }
}