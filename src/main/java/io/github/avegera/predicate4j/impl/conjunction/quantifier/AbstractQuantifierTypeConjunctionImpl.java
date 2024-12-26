package io.github.avegera.predicate4j.impl.conjunction.quantifier;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.common.WhereBoolean;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;
import io.github.avegera.predicate4j.api.type.common.WhereString;
import io.github.avegera.predicate4j.api.conjunction.quantifier.WhereQuantifierConjunction;
import io.github.avegera.predicate4j.impl.entry.quantifier.type.WhereQuantifierBooleanImpl;
import io.github.avegera.predicate4j.impl.entry.quantifier.type.WhereQuantifierNumberImpl;
import io.github.avegera.predicate4j.impl.entry.quantifier.type.WhereQuantifierStringImpl;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQuantifierTypeConjunctionImpl<T> implements WhereQuantifierConjunction<T> {

    private final FluentPredicate<T> previousPredicate;

    public AbstractQuantifierTypeConjunctionImpl(FluentPredicate<T> previousPredicate) {
        this.previousPredicate = previousPredicate;
    }

    @Override
    public WhereBoolean<T> booleanValue(Function<T, Iterable<Boolean>> mapper) {
        return new WhereQuantifierBooleanImpl<>(getQuantifierFunction(), mapper, previousPredicate);
    }

    @Override
    public <N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, Iterable<N>> mapper) {
        return new WhereQuantifierNumberImpl<>(getQuantifierFunction(), mapper, previousPredicate);
    }

    @Override
    public WhereString<T> string(Function<T, Iterable<String>> mapper) {
        return new WhereQuantifierStringImpl<>(getQuantifierFunction(), mapper, previousPredicate);
    }

    abstract protected <E> BiFunction<Iterable<E>, Predicate<E>, Boolean> getQuantifierFunction();
}