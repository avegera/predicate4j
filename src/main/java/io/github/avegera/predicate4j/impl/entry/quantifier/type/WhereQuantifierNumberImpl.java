package io.github.avegera.predicate4j.impl.entry.quantifier.type;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.entry.quantifier.Quantifier;
import io.github.avegera.predicate4j.impl.type.common.WhereNumberImpl;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereQuantifierNumberImpl<T, N extends Number & Comparable<N>> extends WhereNumberImpl<T, Iterable<N>, N> {

    private final Quantifier quantifier;

    public WhereQuantifierNumberImpl(Quantifier quantifier, Function<T, Iterable<N>> mapper) {
        super(mapper);
        this.quantifier = quantifier;
    }

    public WhereQuantifierNumberImpl(Quantifier quantifier, Function<T, Iterable<N>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
        this.quantifier = quantifier;
    }

    @Override
    protected boolean test(Function<T, Iterable<N>> mapper, Predicate<N> predicate, T object) {
        return quantifier.apply(mapper.apply(object), predicate);
    }
}