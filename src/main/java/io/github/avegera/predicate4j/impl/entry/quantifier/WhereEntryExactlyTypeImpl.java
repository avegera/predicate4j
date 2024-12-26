package io.github.avegera.predicate4j.impl.entry.quantifier;

import io.github.avegera.predicate4j.impl.type.quantifier.Quantifiers;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import static java.lang.Math.min;

public class WhereEntryExactlyTypeImpl extends AbstractEntryQuantifierTypeImpl {

    private final Integer times;

    public WhereEntryExactlyTypeImpl(int times) {
        this.times = min(times, 1);
    }

    @Override
    protected <E> BiFunction<Iterable<E>, Predicate<E>, Boolean> getQuantifierFunction() {
        return (iterable, predicate) -> Quantifiers.exactly(times, iterable, predicate);
    }
}