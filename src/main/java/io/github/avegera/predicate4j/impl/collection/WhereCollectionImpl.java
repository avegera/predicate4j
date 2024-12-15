package io.github.avegera.predicate4j.impl.collection;

import io.github.avegera.predicate4j.Predicates;
import io.github.avegera.predicate4j.api.RichPredicate;
import io.github.avegera.predicate4j.api.WhereNumber;
import io.github.avegera.predicate4j.api.collection.WhereCollection;
import io.github.avegera.predicate4j.impl.WhereNumberImpl;

import java.util.Collection;
import java.util.function.Function;

public class WhereCollectionImpl<T, R extends Collection<E>, E> extends WhereIterableImpl<T, R, E> implements WhereCollection<T, R, E> {

    public WhereCollectionImpl(Function<T, R> mapper) {
        super(mapper);
    }

    public WhereCollectionImpl(Function<T, R> mapper, RichPredicate<T> previousPredicate) {
        super(mapper, previousPredicate);
    }

    @Override
    public RichPredicate<T> contains(E element) {
        return getPredicate(Predicates.containsElement(element));
    }

    @Override
    public RichPredicate<T> notContains(E element) {
        return getPredicate(Predicates.notContainsElement(element));
    }

    @Override
    public RichPredicate<T> containsAll(Collection<E> elements) {
        return getPredicate(Predicates.containsAllElements(elements));
    }

    @Override
    public RichPredicate<T> notContainsAll(Collection<E> elements) {
        return getPredicate(Predicates.notContainsAllElements(elements));
    }

    @Override
    public WhereNumber<T, Integer> size() {
        return new WhereNumberImpl<>(mapper == null ? null : this::getSize, previousPredicate);
    }

    private Integer getSize(T object) {
        return getInt(object, Collection::size);
    }
}