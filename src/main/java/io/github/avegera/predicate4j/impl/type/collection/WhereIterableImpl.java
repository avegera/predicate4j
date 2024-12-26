package io.github.avegera.predicate4j.impl.type.collection;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.collection.WhereIterable;
import io.github.avegera.predicate4j.impl.type.common.WhereObjectImpl;

import java.util.function.Function;

public class WhereIterableImpl<T, R extends Iterable<E>, E> extends WhereObjectImpl<T, R> implements WhereIterable<T, R, E> {

    public WhereIterableImpl(Function<T, R> mapper) {
        super(mapper);
    }

    public WhereIterableImpl(Function<T, R> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public FluentPredicate<T> isEmpty() {
        return getPredicate(iterable -> iterable == null || iterable.iterator() == null || !iterable.iterator().hasNext());
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public FluentPredicate<T> notEmpty() {
        return getPredicate(iterable -> iterable != null && iterable.iterator() != null && iterable.iterator().hasNext());
    }
}