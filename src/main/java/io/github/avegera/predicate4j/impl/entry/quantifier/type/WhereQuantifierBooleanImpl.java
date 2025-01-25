package io.github.avegera.predicate4j.impl.entry.quantifier.type;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.entry.quantifier.Quantifier;
import io.github.avegera.predicate4j.impl.type.common.WhereBooleanImpl;

import java.util.function.Function;
import java.util.function.Predicate;

public class WhereQuantifierBooleanImpl<T> extends WhereBooleanImpl<T, Iterable<Boolean>> {

    private final Quantifier quantifier;

    public WhereQuantifierBooleanImpl(Quantifier quantifier, Function<T, Iterable<Boolean>> mapper) {
        super(mapper);
        this.quantifier = quantifier;
    }

    public WhereQuantifierBooleanImpl(Quantifier quantifier, Function<T, Iterable<Boolean>> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
        this.quantifier = quantifier;
    }

    @Override
    protected boolean test(Function<T, Iterable<Boolean>> mapper, Predicate<Boolean> predicate, T object) {
        return quantifier.apply(mapper.apply(object), predicate);
    }
}