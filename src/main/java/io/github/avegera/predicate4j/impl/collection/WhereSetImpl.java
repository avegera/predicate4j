package io.github.avegera.predicate4j.impl.collection;

import io.github.avegera.predicate4j.api.FluentPredicate;
import io.github.avegera.predicate4j.api.collection.WhereSet;

import java.util.Set;
import java.util.function.Function;

public class WhereSetImpl<T, R extends Set<E>, E> extends WhereCollectionImpl<T, R, E> implements WhereSet<T, R, E> {

    public WhereSetImpl(Function<T, R> mapper) {
        super(mapper);
    }

    public WhereSetImpl(Function<T, R> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }
}