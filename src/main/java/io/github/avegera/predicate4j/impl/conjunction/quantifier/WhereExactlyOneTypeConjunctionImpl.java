package io.github.avegera.predicate4j.impl.conjunction.quantifier;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.impl.type.quantifier.Quantifiers;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class WhereExactlyOneTypeConjunctionImpl<T> extends AbstractQuantifierTypeConjunctionImpl<T> {

    public WhereExactlyOneTypeConjunctionImpl(FluentPredicate<T> previousPredicate) {
        super(previousPredicate);
    }

    @Override
    protected <E> BiFunction<Iterable<E>, Predicate<E>, Boolean> getQuantifierFunction() {
        return Quantifiers::exactlyOne;
    }
}