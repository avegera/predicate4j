package io.github.avegera.predicate4j.impl;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.RichPredicate;
import io.github.avegera.predicate4j.api.WhereList;
import io.github.avegera.predicate4j.api.WhereNumber;

import java.util.List;
import java.util.function.Function;

public class WhereListImpl<T, R> extends WhereObjectImpl<T, List<R>> implements WhereList<T, R> {

    public WhereListImpl(Function<T, List<R>> mapper) {
        super(mapper);
    }

    public WhereListImpl(Function<T, List<R>> mapper, RichPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
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
    public RichPredicate<T> contains(R element) {
        return getPredicate(Predicates.containsElement(element));
    }

    @Override
    public RichPredicate<T> notContains(R element) {
        return getPredicate(Predicates.notContainsElement(element));
    }

    @Override
    public WhereNumber<T, Integer> size() {
        return new WhereNumberImpl<>(mapper == null ? null : this::getSize, previousPredicate);
    }

    private Integer getSize(T object) {
        return getInt(object, List::size);
    }
}