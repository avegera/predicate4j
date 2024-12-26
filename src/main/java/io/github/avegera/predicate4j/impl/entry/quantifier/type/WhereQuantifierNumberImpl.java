package io.github.avegera.predicate4j.impl.entry.quantifier.type;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.type.common.WhereNumberImpl;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class WhereQuantifierNumberImpl<T, N extends Number & Comparable<N>> extends WhereNumberImpl<T, Iterable<N>, N> {

    private final BiFunction<Iterable<N>, Predicate<N>, Boolean> quantifier;

    public WhereQuantifierNumberImpl(BiFunction<Iterable<N>, Predicate<N>, Boolean> quantifier, Function<T, Iterable<N>> mapper) {
        super(mapper);
        this.quantifier = quantifier;
    }

    public WhereQuantifierNumberImpl(BiFunction<Iterable<N>, Predicate<N>, Boolean> quantifier, Function<T, Iterable<N>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
        this.quantifier = quantifier;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    protected boolean test(Function<T, Iterable<N>> mapper, Predicate<N> predicate, T object) {
        Iterable<N> iterable = mapper.apply(object);
        if (iterable == null || iterable.iterator() == null) {
            return false;
        }
        return quantifier.apply(iterable, predicate);
    }
}