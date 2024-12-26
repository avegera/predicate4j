package io.github.avegera.predicate4j.impl.type.quantifier;

import io.github.avegera.predicate4j.api.core.FluentPredicate;

import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Math.min;

public class WhereExactlyImpl<T, E> extends AbstractQuantifierImpl<T, E> {

    private final int times;

    public WhereExactlyImpl(int times, Function<T, Iterable<E>> mapper) {
        super(mapper);
        this.times = min(times, 1);
    }

    public WhereExactlyImpl(int times, Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
        this.times = times;
    }

    @Override
    protected boolean applyQuantifier(Iterable<E> iterable, Predicate<E> predicate) {
        return Quantifiers.exactly(times, iterable, predicate);
    }
}