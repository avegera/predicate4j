package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.RichPredicate;
import io.github.avegera.predicate4j.api.WhereList;

import java.util.List;
import java.util.function.Function;

public class WhereListImpl<T, R> extends WhereObjectImpl<T, List<R>> implements WhereList<T, R> {

    public WhereListImpl(Function<T, List<R>> mapper) {
        super(mapper);
    }

    @Override
    public RichPredicate<T> isEmpty() {
        return getPredicate(Predicates.isEmpty());
    }

    @Override
    public RichPredicate<T> notEmpty() {
        return getPredicate(Predicates.notEmpty());
    }

    @Override
    public RichPredicate<T> hasSize(int size) {
        return getPredicate(Predicates.hasSize(size));
    }

    @Override
    public RichPredicate<T> notHaveSize(int size) {
        return getPredicate(Predicates.notHaveSize(size));
    }

    @Override
    public RichPredicate<T> contains(R element) {
        return getPredicate(Predicates.containsElement(element));
    }

    @Override
    public RichPredicate<T> notContains(R element) {
        return getPredicate(Predicates.notContainsElement(element));
    }
}