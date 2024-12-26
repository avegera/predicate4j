package io.github.avegera.predicate4j.impl.entry.quantifier;

import io.github.avegera.predicate4j.impl.type.quantifier.Quantifiers;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class WhereEntryAtLeastOneTypeImpl extends AbstractEntryQuantifierTypeImpl {

    @Override
    protected <E> BiFunction<Iterable<E>, Predicate<E>, Boolean> getQuantifierFunction() {
        return Quantifiers::atLeastOne;
    }
}