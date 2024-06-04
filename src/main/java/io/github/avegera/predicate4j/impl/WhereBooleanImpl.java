package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.RichPredicate;
import io.github.avegera.predicate4j.api.WhereBoolean;

import java.util.function.Function;

public class WhereBooleanImpl<T> extends WhereObjectImpl<T, Boolean> implements WhereBoolean<T> {

    public WhereBooleanImpl(Function<T, Boolean> mapper) {
        super(mapper);
    }

    @Override
    public RichPredicate<T> isTrue() {
        return getPredicate(Predicates.isTrue());
    }

    @Override
    public RichPredicate<T> notTrue() {
        return getPredicate(Predicates.notTrue());
    }

    @Override
    public RichPredicate<T> isFalse() {
        return getPredicate(Predicates.isFalse());
    }

    @Override
    public RichPredicate<T> notFalse() {
        return getPredicate(Predicates.notFalse());
    }
}