package io.github.avegera.predicate4j.impl.conjunction.quantifier;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.type.quantifier.Quantifiers;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class WhereAtLeastTypeConjunctionImpl<T> extends AbstractQuantifierTypeConjunctionImpl<T> {

    private final Integer times;

    public WhereAtLeastTypeConjunctionImpl(int times, FluentPredicate<T> previousPredicate) {
        super(previousPredicate);
        this.times = times;
    }

    @Override
    protected <E> BiFunction<Iterable<E>, Predicate<E>, Boolean> getQuantifierFunction() {
        return (iterable, predicate) -> Quantifiers.atLeast(times, iterable, predicate);
    }
}