package io.github.avegera.predicate4j.impl.type.collection;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.collection.WhereList;

import java.util.List;
import java.util.function.Function;

public class WhereListImpl<T, R extends List<E>, E> extends WhereCollectionImpl<T, R, E> implements WhereList<T, R, E> {

    public WhereListImpl(Function<T, R> mapper) {
        super(mapper);
    }

    public WhereListImpl(Function<T, R> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }
}