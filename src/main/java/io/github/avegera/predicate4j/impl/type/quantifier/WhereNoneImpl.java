package io.github.avegera.predicate4j.impl.type.quantifier;

import io.github.avegera.predicate4j.api.core.FluentPredicate;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereNoneImpl<T, E> extends AbstractQuantifierImpl<T, E> {

    public WhereNoneImpl(Function<T, Iterable<E>> mapper) {
        super(mapper);
    }

    public WhereNoneImpl(Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    protected boolean applyQuantifier(Iterable<E> iterable, Predicate<E> predicate) {
        return Quantifiers.none(iterable, predicate);
    }
}