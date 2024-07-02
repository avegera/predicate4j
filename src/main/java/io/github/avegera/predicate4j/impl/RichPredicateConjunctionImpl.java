package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.api.*;

import java.util.List;
import java.util.function.Function;

public class RichPredicateConjunctionImpl<T> implements RichPredicateConjunction<T> {

    private final RichPredicate<T> previousPredicate;

    public RichPredicateConjunctionImpl(RichPredicate<T> previousPredicate) {
        this.previousPredicate = previousPredicate;
    }

    @Override
    public WhereBoolean<T> booleanValue(Function<T, Boolean> mapper) {
        return new WhereBooleanImpl<>(mapper, previousPredicate);
    }

    @Override
    public <R> WhereList<T, R> list(Function<T, List<R>> mapper) {
        return new WhereListImpl<>(mapper, previousPredicate);
    }

    @Override
    public WhereString<T> string(Function<T, String> mapper) {
        return new WhereStringImpl<>(mapper, previousPredicate);
    }
}