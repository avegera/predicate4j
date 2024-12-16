package io.github.avegera.predicate4j.impl.quantifier;

import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.quantifier.WhereNone;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereNoneImpl<T, E> extends AbstractQuantifier<T, E> implements WhereNone<T, E> {

    public WhereNoneImpl(Function<T, Iterable<E>> mapper) {
        super(mapper);
    }

    public WhereNoneImpl(Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    protected boolean test(Iterable<E> iterable, Predicate<E> predicate) {
        for (E element : iterable) {
            if (predicate.test(element)) {
                return false;
            }
        }
        return true;
    }
}