package io.github.avegera.predicate4j.api.type.collection;

import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.api.type.common.WhereNumber;

import java.util.Collection;

public interface WhereCollection<T, R extends Collection<E>, E> extends WhereIterable<T, R, E> {

    WhereNumber<T, Integer> size();

    FluentPredicate<T> contains(E element);

    FluentPredicate<T> notContains(E element);

    FluentPredicate<T> containsAll(Collection<E> elements);

    FluentPredicate<T> notContainsAll(Collection<E> elements);
}