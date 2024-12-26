package io.github.avegera.predicate4j.impl.type.quantifier;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.type.common.WhereObjectImpl;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQuantifierImpl<T, E> extends WhereObjectImpl<T, Iterable<E>, E> {

    public AbstractQuantifierImpl(Function<T, Iterable<E>> mapper) {
        super(mapper);
    }

    public AbstractQuantifierImpl(Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    protected boolean test(Function<T, Iterable<E>> mapper, Predicate<E> predicate, T object) {
        Iterable<E> iterable = mapper.apply(object);
        if (iterable == null || iterable.iterator() == null) {
            return false;
        }
        return applyQuantifier(iterable, predicate);
    }

    abstract protected boolean applyQuantifier(Iterable<E> iterable, Predicate<E> predicate);
}