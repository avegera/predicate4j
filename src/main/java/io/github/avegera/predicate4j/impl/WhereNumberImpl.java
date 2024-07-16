package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.RichPredicate;
import io.github.avegera.predicate4j.api.WhereNumber;

import java.util.function.Function;

public class WhereNumberImpl<T, N extends Number & Comparable<N>> extends WhereObjectImpl<T, N> implements WhereNumber<T, N> {

    public WhereNumberImpl(Function<T, N> mapper) {
        super(mapper);
    }

    public WhereNumberImpl(Function<T, N> mapper, RichPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    public RichPredicate<T> isGreaterThan(N value) {
        return getPredicate(Predicates.isGreaterThan(value));
    }

    @Override
    public RichPredicate<T> isLessThan(N value) {
        return getPredicate(Predicates.isLessThan(value));
    }

    @Override
    public RichPredicate<T> isGreaterThanOrEqualTo(N value) {
        return getPredicate(Predicates.isGreaterThanOrEqualTo(value));
    }

    @Override
    public RichPredicate<T> isLessThanOrEqualTo(N value) {
        return getPredicate(Predicates.isLessThanOrEqualTo(value));
    }

    @Override
    public RichPredicate<T> isBetween(N startInclusive, N endInclusive) {
        return getPredicate(Predicates.isBetween(startInclusive, endInclusive));
    }

    @Override
    public RichPredicate<T> notBetween(N startInclusive, N endInclusive) {
        return getPredicate(Predicates.notBetween(startInclusive, endInclusive));
    }

    @Override
    public RichPredicate<T> isEven() {
        return getPredicate(Predicates.isEven());
    }

    @Override
    public RichPredicate<T> isOdd() {
        return getPredicate(Predicates.isOdd());
    }
}