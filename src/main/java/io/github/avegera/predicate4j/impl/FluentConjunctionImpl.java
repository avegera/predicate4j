package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.api.*;
import io.github.avegera.predicate4j.api.collection.WhereCollection;
import io.github.avegera.predicate4j.api.collection.WhereIterable;
import io.github.avegera.predicate4j.api.collection.WhereList;
import io.github.avegera.predicate4j.api.collection.WhereSet;
import io.github.avegera.predicate4j.api.quantifier.WhereAtLeast;
import io.github.avegera.predicate4j.api.quantifier.WhereEach;
import io.github.avegera.predicate4j.api.quantifier.WhereExactly;
import io.github.avegera.predicate4j.api.quantifier.WhereNone;
import io.github.avegera.predicate4j.impl.collection.WhereCollectionImpl;
import io.github.avegera.predicate4j.impl.collection.WhereIterableImpl;
import io.github.avegera.predicate4j.impl.collection.WhereListImpl;
import io.github.avegera.predicate4j.impl.collection.WhereSetImpl;
import io.github.avegera.predicate4j.impl.quantifier.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FluentConjunctionImpl<T> implements FluentConjunction<T> {

    private final FluentPredicate<T> previousPredicate;

    public FluentConjunctionImpl(FluentPredicate<T> previousPredicate) {
        this.previousPredicate = previousPredicate;
    }

    @Override
    public WhereBoolean<T> booleanValue(Function<T, Boolean> mapper) {
        return getWhere(WhereBooleanImpl::new, mapper);
    }

    @Override
    public <E> WhereCollection<T, Collection<E>, E> collection(Function<T, Collection<E>> mapper) {
        return getWhere(WhereCollectionImpl::new, mapper);
    }

    @Override
    public <E> WhereIterable<T, Iterable<E>, E> iterable(Function<T, Iterable<E>> mapper) {
        return getWhere(WhereIterableImpl::new, mapper);
    }

    @Override
    public <E> WhereList<T, List<E>, E> list(Function<T, List<E>> mapper) {
        return getWhere(WhereListImpl::new, mapper);
    }

    @Override
    public <E> WhereSet<T, Set<E>, E> set(Function<T, Set<E>> mapper) {
        return getWhere(WhereSetImpl::new, mapper);
    }

    @Override
    public <N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper) {
        return getWhere(WhereNumberImpl::new, mapper);
    }

    @Override
    public WhereString<T> string(Function<T, String> mapper) {
        return getWhere(WhereStringImpl::new, mapper);
    }

    @Override
    public <E> WhereAtLeast<T, E> atLeast(int times, Function<T, Iterable<E>> mapper) {
        return new WhereAtLeastImpl<>(times, mapper, previousPredicate);
    }

    @Override
    public <E> WhereAtLeast<T, E> atLeastOne(Function<T, Iterable<E>> mapper) {
        return new WhereAtLeastOneImpl<>(mapper, previousPredicate);
//        return getWhere(WhereAtLeastImpl::new, mapper); //TODO: yeah, it's a bit different logic
    }

    @Override
    public <E> WhereExactly<T, E> exactly(int times, Function<T, Iterable<E>> mapper) {
        return new WhereExactlyImpl<>(times, mapper, previousPredicate);
    }

    @Override
    public <E> WhereExactly<T, E> exactlyOne(Function<T, Iterable<E>> mapper) {
        return new WhereExactlyOneImpl<>(mapper, previousPredicate);
    }

    @Override
    public <E> WhereEach<T, E> each(Function<T, Iterable<E>> mapper) {
        return new WhereEachImpl<>(mapper, previousPredicate);
    }

    @Override
    public <E> WhereNone<T, E> none(Function<T, Iterable<E>> mapper) {
        return new WhereNoneImpl<>(mapper, previousPredicate);
    }

    private <R, W extends WhereObject<T, R>> W getWhere(BiFunction<Function<T, R>, FluentPredicate<T>, W> constructor, Function<T, R> mapper) {
        return constructor.apply(mapper, previousPredicate);
    }
}