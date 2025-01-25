package io.github.avegera.predicate4j.impl.conjunction.quantifier;

import io.github.avegera.predicate4j.Quantifiers;
import io.github.avegera.predicate4j.api.conjunction.quantifier.WhereConjunctionQuantifierType;
import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.common.WhereBoolean;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;
import io.github.avegera.predicate4j.api.type.common.WhereString;
import io.github.avegera.predicate4j.impl.entry.quantifier.Quantifier;
import io.github.avegera.predicate4j.impl.entry.quantifier.type.WhereQuantifierBooleanImpl;
import io.github.avegera.predicate4j.impl.entry.quantifier.type.WhereQuantifierNumberImpl;
import io.github.avegera.predicate4j.impl.entry.quantifier.type.WhereQuantifierStringImpl;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereEachTypeConjunctionImpl<T> implements Quantifier, WhereConjunctionQuantifierType<T> {

    private final FluentPredicate<T> previousPredicate;

    public WhereEachTypeConjunctionImpl(FluentPredicate<T> previousPredicate) {
        this.previousPredicate = previousPredicate;
    }

    @Override
    public <E> boolean apply(Iterable<E> iterable, Predicate<E> predicate) {
        return Quantifiers.each(iterable, predicate);
    }

    @Override
    public WhereBoolean<T> booleanValue(Function<T, Iterable<Boolean>> mapper) {
        return new WhereQuantifierBooleanImpl<>(this, mapper, previousPredicate);
    }

    @Override
    public <N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, Iterable<N>> mapper) {
        return new WhereQuantifierNumberImpl<>(this, mapper, previousPredicate);
    }

    @Override
    public WhereString<T> string(Function<T, Iterable<String>> mapper) {
        return new WhereQuantifierStringImpl<>(this, mapper, previousPredicate);
    }
}