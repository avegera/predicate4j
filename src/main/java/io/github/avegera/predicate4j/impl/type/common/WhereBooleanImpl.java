package io.github.avegera.predicate4j.impl.type.common;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.common.WhereBoolean;

import java.util.function.Function;

public class WhereBooleanImpl<T> extends WhereObjectImpl<T, Boolean> implements WhereBoolean<T> {

    public WhereBooleanImpl(Function<T, Boolean> mapper) {
        super(mapper);
    }

    public WhereBooleanImpl(Function<T, Boolean> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    public FluentPredicate<T> isTrue() {
        return getPredicate(Predicates.isTrue());
    }

    @Override
    public FluentPredicate<T> notTrue() {
        return getPredicate(Predicates.notTrue());
    }

    @Override
    public FluentPredicate<T> isFalse() {
        return getPredicate(Predicates.isFalse());
    }

    @Override
    public FluentPredicate<T> notFalse() {
        return getPredicate(Predicates.notFalse());
    }
}