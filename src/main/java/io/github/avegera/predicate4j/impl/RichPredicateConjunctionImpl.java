package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.api.*;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RichPredicateConjunctionImpl<T> implements RichPredicateConjunction<T> {

    private final RichPredicate<T> previousPredicate;

    public RichPredicateConjunctionImpl(RichPredicate<T> previousPredicate) {
        this.previousPredicate = previousPredicate;
    }

    @Override
    public WhereBoolean<T> booleanValue(Function<T, Boolean> mapper) {
        return getWhere(WhereBooleanImpl::new, mapper);
    }

    @Override
    public <R> WhereList<T, R> list(Function<T, List<R>> mapper) {
        return getWhere(WhereListImpl::new, mapper);
    }

    @Override
    public <N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, N> mapper) {
        return getWhere(WhereNumberImpl::new, mapper);
    }

    @Override
    public WhereString<T> string(Function<T, String> mapper) {
        return getWhere(WhereStringImpl::new, mapper);
    }

    private <R, W extends WhereObject<T, R>> W getWhere(BiFunction<Function<T, R>, RichPredicate<T>, W> constructor, Function<T, R> mapper) {
        return constructor.apply(mapper, previousPredicate);
    }
}