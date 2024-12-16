package io.github.avegera.predicate4j.impl.quantifier;

import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.quantifier.WhereAtLeast;
import io.github.avegera.predicate4j.impl.FluentPredicateImpl;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereAtLeastImpl<T, E> extends AbstractQuantifier<T, E> implements WhereAtLeast<T, E> {

    private final int times;

    public WhereAtLeastImpl(int times, Function<T, Iterable<E>> mapper) {
        super(mapper);
        this.times = times;
        //TODO: validate times (only positive) - or just ignore if negative
    }

    public WhereAtLeastImpl(int times, Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
        this.times = times;
    }

    protected boolean test(Iterable<E> iterable, Predicate<E> predicate) {
        int count = 0;
        for (E element : iterable) {
            if (!predicate.test(element)) {
                continue;
            }

            count++;
            if (count >= this.times) {
                return true;
            }
        }
        return false;
    }
}