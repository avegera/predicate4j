package io.github.avegera.predicate4j.impl.entry.quantifier;

import io.github.avegera.predicate4j.api.type.common.WhereBoolean;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;
import io.github.avegera.predicate4j.api.type.common.WhereString;
import io.github.avegera.predicate4j.api.entry.quantifier.WhereEntryQuantifierType;
import io.github.avegera.predicate4j.impl.entry.quantifier.type.WhereQuantifierBooleanImpl;
import io.github.avegera.predicate4j.impl.entry.quantifier.type.WhereQuantifierNumberImpl;
import io.github.avegera.predicate4j.impl.entry.quantifier.type.WhereQuantifierStringImpl;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractEntryQuantifierTypeImpl implements WhereEntryQuantifierType {

    @Override
    public <T> WhereBoolean<T> booleanValue(Function<T, Iterable<Boolean>> mapper) {
        return new WhereQuantifierBooleanImpl<>(getQuantifierFunction(), mapper);
    }

    @Override
    public <T, N extends Number & Comparable<N>> WhereNumber<T, N> number(Function<T, Iterable<N>> mapper) {
        return new WhereQuantifierNumberImpl<>(getQuantifierFunction(), mapper);
    }

    @Override
    public <T> WhereString<T> string(Function<T, Iterable<String>> mapper) {
        return new WhereQuantifierStringImpl<>(getQuantifierFunction(), mapper);
    }

    abstract protected <E> BiFunction<Iterable<E>, Predicate<E>, Boolean> getQuantifierFunction();
}