package io.github.avegera.predicate4j.impl.conjunction.quantifier;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.type.quantifier.Quantifiers;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class WhereExactlyTypeConjunctionImpl<T> extends AbstractQuantifierTypeConjunctionImpl<T> {

    private final Integer times;


    public WhereExactlyTypeConjunctionImpl(int times, FluentPredicate<T> previousPredicate) {
        super(previousPredicate);
        this.times = times;
    }

    @Override
    protected <E> BiFunction<Iterable<E>, Predicate<E>, Boolean> getQuantifierFunction() {
        return (iterable, predicate) -> Quantifiers.exactly(times, iterable, predicate);
    }
}