package io.github.avegera.predicate4j.impl.type.quantifier;

import io.github.avegera.predicate4j.Quantifiers;
import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.type.common.WhereObjectImpl;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereEachImpl<T, E> extends WhereObjectImpl<T, Iterable<E>, E> {

    public WhereEachImpl(Function<T, Iterable<E>> mapper) {
        super(mapper);
    }

    public WhereEachImpl(Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    protected boolean test(Function<T, Iterable<E>> mapper, Predicate<E> predicate, T object) {
        return Quantifiers.each(mapper.apply(object), predicate);
    }
}