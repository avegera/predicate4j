package io.github.avegera.predicate4j.impl.core;

import io.github.avegera.predicate4j.api.conjunction.common.WhereConjunction;
import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.common.WhereObject;
import io.github.avegera.predicate4j.impl.conjuction.common.WhereConjunctionImpl;
import io.github.avegera.predicate4j.impl.type.common.WhereObjectImpl;

import java.util.function.Function;
import java.util.function.Predicate;

public class FluentPredicateImpl<T> implements FluentPredicate<T> {

    private final Predicate<T> predicate;

    public FluentPredicateImpl(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(T object) {
        return predicate.test(object);
    }

    @Override
    public <R2> WhereObject<T, R2> and(Function<T, R2> andMapper) {
        return new WhereObjectImpl<>(andMapper, this);
    }

    @Override
    public WhereConjunction<T> and() {
        return new WhereConjunctionImpl<>(this);
    }
}