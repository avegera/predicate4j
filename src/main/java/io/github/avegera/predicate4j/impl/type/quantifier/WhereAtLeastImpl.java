package io.github.avegera.predicate4j.impl.type.quantifier;

import io.github.avegera.predicate4j.Quantifiers;
import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.type.common.WhereObjectImpl;

import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Math.max;

public class WhereAtLeastImpl<T, E> extends WhereObjectImpl<T, Iterable<E>, E> {

    private final int times;

    public WhereAtLeastImpl(int times, Function<T, Iterable<E>> mapper) {
        super(mapper);
        this.times = max(times, 1);
    }

    public WhereAtLeastImpl(int times, Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
        this.times = times;
    }

    @Override
    protected boolean test(Function<T, Iterable<E>> mapper, Predicate<E> predicate, T object) {
        Iterable<E> iterable = mapper.apply(object);
        return Quantifiers.atLeast(times, iterable, predicate);
    }
}