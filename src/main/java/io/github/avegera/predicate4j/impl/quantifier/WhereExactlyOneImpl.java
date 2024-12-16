package io.github.avegera.predicate4j.impl.quantifier;

import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.quantifier.WhereAtLeast;
import io.github.avegera.predicate4j.api.quantifier.WhereExactly;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereExactlyOneImpl<T, E> extends AbstractQuantifier<T, E> implements WhereExactly<T, E> {

    public WhereExactlyOneImpl(Function<T, Iterable<E>> mapper) {
        super(mapper);
    }

    public WhereExactlyOneImpl(Function<T, Iterable<E>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    protected boolean test(Iterable<E> iterable, Predicate<E> predicate) {
        short count = 0;
        for (E element : iterable) {
            if (!predicate.test(element)) {
                continue;
            }

            count++;
            if (count > 1) {
                return false;
            }
        }
        return count == 1;
    }
}