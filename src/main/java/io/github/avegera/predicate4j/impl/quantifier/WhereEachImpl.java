package io.github.avegera.predicate4j.impl.quantifier;

import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.quantifier.WhereEach;
import io.github.avegera.predicate4j.api.quantifier.WhereNone;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereEachImpl<T, E> extends AbstractQuantifier<T, E> implements WhereEach<T, E> {

    public WhereEachImpl(Function<T, Iterable<E>> mapper) {
        super(mapper);
    }

    public WhereEachImpl(Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    protected boolean test(Iterable<E> iterable, Predicate<E> predicate) {
        for (E element : iterable) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        //TODO: for empty collection it will be always false? use logic from Stream API allMatch
        return true;
    }
}