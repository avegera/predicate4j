package io.github.avegera.predicate4j.impl.quantifier;

import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.quantifier.WhereAtLeast;
import io.github.avegera.predicate4j.api.quantifier.WhereExactly;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereExactlyImpl<T, E> extends AbstractQuantifier<T, E> implements WhereExactly<T, E> {

    private final int times;

    public WhereExactlyImpl(int times, Function<T, Iterable<E>> mapper) {
        super(mapper);
        this.times = times;
    }

    public WhereExactlyImpl(int times, Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
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
            if (count > this.times) {
                return false;
            }
        }
        return count == this.times;
    }
}