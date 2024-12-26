package io.github.avegera.predicate4j.impl.entry.quantifier.type;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.type.common.WhereBooleanImpl;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class WhereQuantifierBooleanImpl<T> extends WhereBooleanImpl<T, Iterable<Boolean>> {

    private final BiFunction<Iterable<Boolean>, Predicate<Boolean>, Boolean> quantifier;

    public WhereQuantifierBooleanImpl(BiFunction<Iterable<Boolean>, Predicate<Boolean>, Boolean> quantifier, Function<T, Iterable<Boolean>> mapper) {
        super(mapper);
        this.quantifier = quantifier;
    }

    public WhereQuantifierBooleanImpl(BiFunction<Iterable<Boolean>, Predicate<Boolean>, Boolean> quantifier, Function<T, Iterable<Boolean>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
        this.quantifier = quantifier;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    protected boolean test(Function<T, Iterable<Boolean>> mapper, Predicate<Boolean> predicate, T object) {
        Iterable<Boolean> iterable = mapper.apply(object);
        if (iterable == null || iterable.iterator() == null) {
            return false;
        }
        return quantifier.apply(iterable, predicate);
    }
}