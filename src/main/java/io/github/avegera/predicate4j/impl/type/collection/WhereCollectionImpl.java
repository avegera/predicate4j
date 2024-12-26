package io.github.avegera.predicate4j.impl.type.collection;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.collection.WhereCollection;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;
import io.github.avegera.predicate4j.impl.type.common.WhereNumberImpl;

import java.util.Collection;
import java.util.function.Function;

public class WhereCollectionImpl<T, R extends Collection<E>, E> extends WhereIterableImpl<T, R, E> implements WhereCollection<T, R, E> {

    public WhereCollectionImpl(Function<T, R> mapper) {
        super(mapper);
    }

    public WhereCollectionImpl(Function<T, R> mapper, FluentPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    public FluentPredicate<T> contains(E element) {
        return getPredicate(Predicates.containsElement(element));
    }

    @Override
    public FluentPredicate<T> notContains(E element) {
        return getPredicate(Predicates.notContainsElement(element));
    }

    @Override
    public FluentPredicate<T> containsAll(Collection<E> elements) {
        return getPredicate(Predicates.containsAllElements(elements));
    }

    @Override
    public FluentPredicate<T> notContainsAll(Collection<E> elements) {
        return getPredicate(Predicates.notContainsAllElements(elements));
    }

    @Override
    public FluentPredicate<T> isEmpty() {
        return getPredicate(Predicates.isEmpty());
    }

    @Override
    public FluentPredicate<T> notEmpty() {
        return getPredicate(Predicates.notEmpty());
    }

    @Override
    public WhereNumber<T, Integer> size() {
        return new WhereNumberImpl<>(mapper == null ? null : this::getSize, previousPredicate);
    }

    private Integer getSize(T object) {
        R result = mapper.apply(object);
        return result != null ? result.size() : 0;
    }
}