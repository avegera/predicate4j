package io.github.avegera.predicate4j.api.collection;

import io.github.avegera.predicate4j.api.RichPredicate;
import io.github.avegera.predicate4j.api.WhereNumber;

import java.util.Collection;

public interface WhereCollection<T, R extends Collection<E>, E> extends WhereIterable<T, R, E> {

    WhereNumber<T, Integer> size();

    RichPredicate<T> contains(E element);

    RichPredicate<T> notContains(E element);

    RichPredicate<T> containsAll(Collection<E> elements);

    RichPredicate<T> notContainsAll(Collection<E> elements);
}