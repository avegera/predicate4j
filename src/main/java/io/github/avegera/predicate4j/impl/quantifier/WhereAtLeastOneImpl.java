package io.github.avegera.predicate4j.impl.quantifier;

import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.quantifier.WhereAtLeast;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereAtLeastOneImpl<T, E> extends AbstractQuantifier<T, E> implements WhereAtLeast<T, E> {

    public WhereAtLeastOneImpl(Function<T, Iterable<E>> mapper) {
        super(mapper);
    }

    public WhereAtLeastOneImpl(Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    protected boolean test(Iterable<E> iterable, Predicate<E> predicate) {
        for (E element : iterable) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }
}