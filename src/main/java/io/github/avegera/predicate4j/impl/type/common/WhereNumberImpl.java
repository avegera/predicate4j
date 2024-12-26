package io.github.avegera.predicate4j.impl.type.common;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;

import java.util.function.Function;

public class WhereNumberImpl<T, N extends Number & Comparable<N>> extends WhereObjectImpl<T, N> implements WhereNumber<T, N> {

    public WhereNumberImpl(Function<T, N> mapper) {
        super(mapper);
    }

    public WhereNumberImpl(Function<T, N> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    public FluentPredicate<T> isGreaterThan(N value) {
        return getPredicate(Predicates.isGreaterThan(value));
    }

    @Override
    public FluentPredicate<T> isLessThan(N value) {
        return getPredicate(Predicates.isLessThan(value));
    }

    @Override
    public FluentPredicate<T> isGreaterThanOrEqualTo(N value) {
        return getPredicate(Predicates.isGreaterThanOrEqualTo(value));
    }

    @Override
    public FluentPredicate<T> isLessThanOrEqualTo(N value) {
        return getPredicate(Predicates.isLessThanOrEqualTo(value));
    }

    @Override
    public FluentPredicate<T> isBetween(N startInclusive, N endInclusive) {
        return getPredicate(Predicates.isBetween(startInclusive, endInclusive));
    }

    @Override
    public FluentPredicate<T> notBetween(N startInclusive, N endInclusive) {
        return getPredicate(Predicates.notBetween(startInclusive, endInclusive));
    }

    @Override
    public FluentPredicate<T> isEven() {
        return getPredicate(Predicates.isEven());
    }

    @Override
    public FluentPredicate<T> isOdd() {
        return getPredicate(Predicates.isOdd());
    }
}