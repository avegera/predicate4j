package io.github.avegera.predicate4j.impl.type.quantifier;

import io.github.avegera.predicate4j.api.core.FluentPredicate;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereEachImpl<T, E> extends AbstractQuantifierImpl<T, E> {

    public WhereEachImpl(Function<T, Iterable<E>> mapper) {
        super(mapper);
    }

    public WhereEachImpl(Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    protected boolean applyQuantifier(Iterable<E> iterable, Predicate<E> predicate) {
        return Quantifiers.each(iterable, predicate);
    }
}