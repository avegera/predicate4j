package io.github.avegera.predicate4j.impl.entry.quantifier.type;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.type.common.WhereStringImpl;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class WhereQuantifierStringImpl<T> extends WhereStringImpl<T, Iterable<String>> {

    private final BiFunction<Iterable<String>, Predicate<String>, Boolean> quantifier;

    public WhereQuantifierStringImpl(BiFunction<Iterable<String>, Predicate<String>, Boolean> quantifier, Function<T, Iterable<String>> mapper) {
        super(mapper);
        this.quantifier = quantifier;
    }

    public WhereQuantifierStringImpl(BiFunction<Iterable<String>, Predicate<String>, Boolean> quantifier, Function<T, Iterable<String>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
        this.quantifier = quantifier;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    protected boolean test(Function<T, Iterable<String>> mapper, Predicate<String> predicate, T object) {
        Iterable<String> iterable = mapper.apply(object);
        if (iterable == null || iterable.iterator() == null) {
            return false;
        }
        return quantifier.apply(iterable, predicate);
    }
}